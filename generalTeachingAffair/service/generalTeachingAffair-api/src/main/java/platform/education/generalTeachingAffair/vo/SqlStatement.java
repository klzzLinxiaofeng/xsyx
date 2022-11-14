package platform.education.generalTeachingAffair.vo;

public class SqlStatement {

    private String sql;

    public SqlStatement(String sql){
        this.sql=sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
