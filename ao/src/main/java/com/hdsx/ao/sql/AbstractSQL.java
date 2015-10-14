package com.hdsx.ao.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 空间数据访问SQL监控
 *   
 * @author jingzh
 * 
 * @createDate 2015-10-14
 * 
 * @email jingzh@hdsxtech.com
 * 
 * @version 1.0
 */
public abstract class AbstractSQL<T> {

  public abstract T getSelf();

  public T INSERT(String tableName) {
	    sql().statementType = SQLStatement.StatementType.INSERT;
	    sql().tables.add(tableName);
	    return getSelf();
  }
  
  public T DELETE(String table) {
	    sql().statementType = SQLStatement.StatementType.DELETE;
	    sql().tables.add(table);
	    return getSelf();
  }
  
  public T UPDATE(String table) {
    sql().statementType = SQLStatement.StatementType.UPDATE;
    sql().tables.add(table);
    return getSelf();
  }
  
  public T SELECT(String columns) {
	    sql().statementType = SQLStatement.StatementType.SELECT;
	    sql().select.add(columns);
	    return getSelf();
  }
  
  public T SET(String sets) {
    sql().sets.add(sets);
    return getSelf();
  }


  public T VALUES(String columns, String values) {
    sql().columns.add(columns);
    sql().values.add(values);
    return getSelf();
  }


  public T FROM(String table) {
    sql().tables.add(table);
    return getSelf();
  }


  public T WHERE(String conditions) {
    sql().where.add(conditions);
    return getSelf();
  }


  public T ORDER_BY(String columns) {
    sql().orderBy.add(columns);
    return getSelf();
  }

  private SQLStatement sql = new SQLStatement();

  private SQLStatement sql() {
    return sql;
  }

  public <A extends Appendable> A usingAppender(A a) {
    sql().sql(a);
    return a;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sql().sql(sb);
    return sb.toString();
  }

  private static class SafeAppendable {
    private final Appendable a;
    private boolean empty = true;

    public SafeAppendable(Appendable a) {
      super();
      this.a = a;
    }

    public SafeAppendable append(CharSequence s) {
      try {
        if (empty && s.length() > 0) empty = false;
        a.append(s);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return this;
    }

    public boolean isEmpty() {
      return empty;
    }

  }

  private static class SQLStatement {

    public enum StatementType {
      DELETE, INSERT, SELECT, UPDATE
    }

    StatementType statementType;
    List<String> sets = new ArrayList<String>();
    List<String> select = new ArrayList<String>();
    List<String> tables = new ArrayList<String>();
    List<String> where = new ArrayList<String>();
    List<String> orderBy = new ArrayList<String>();
    List<String> columns = new ArrayList<String>();
    List<String> values = new ArrayList<String>();

    private void sqlClause(SafeAppendable builder, String keyword, List<String> parts, String open, String close,
                           String conjunction) {
    	if (!parts.isEmpty()) {
    		if (!builder.isEmpty())
    			builder.append("\n");
    		builder.append(keyword);
    		builder.append(" ");
    		builder.append(open);
    		for (int i = 0, n = parts.size(); i < n; i++) {
    			String part = parts.get(i);
    			builder.append(part);
    		}
    		builder.append(close);
    	}
    }

    private String selectSQL(SafeAppendable builder) {
      sqlClause(builder, "SELECT", select, "", "", ", ");
      sqlClause(builder, "FROM", tables, "", "", ", ");
      sqlClause(builder, "WHERE", where, "", "", " ");
      sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
      return builder.toString();
    }

    private String insertSQL(SafeAppendable builder) {
      sqlClause(builder, "INSERT INTO", tables, "", "", "");
      sqlClause(builder, "", columns, "(", ")", ", ");
      sqlClause(builder, "VALUES", values, "(", ")", ", ");
      return builder.toString();
    }

    private String deleteSQL(SafeAppendable builder) {
      sqlClause(builder, "DELETE FROM", tables, "", "", "");
      sqlClause(builder, "WHERE", where, "(", ")", " AND ");
      return builder.toString();
    }

    private String updateSQL(SafeAppendable builder) {

      sqlClause(builder, "UPDATE", tables, "", "", "");
      sqlClause(builder, "SET", sets, "", "", ", ");
      sqlClause(builder, "WHERE", where, "(", ")", " AND ");
      return builder.toString();
    }

    public String sql(Appendable a) {
      SafeAppendable builder = new SafeAppendable(a);
      if (statementType == null) {
        return null;
      }

      String answer;

      switch (statementType) {
        case DELETE:
          answer = deleteSQL(builder);
          break;

        case INSERT:
          answer = insertSQL(builder);
          break;

        case SELECT:
          answer = selectSQL(builder);
          break;

        case UPDATE:
          answer = updateSQL(builder);
          break;

        default:
          answer = null;
      }

      return answer;
    }
  }
}
