package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class QueryFactory {
    private static final String INSERT = "INSERT INTO @(#) VALUES($)";
    private static final String UPDATE = "UPDATE @ SET # WHERE $";
    private static final String SELECT = "SELECT @ FROM # WHERE $";
    private static final String SIMPLE_SELECT = "SELECT @ FROM #";

    public String mount_insert(String tableName, ArrayList<String> columns) {
        return INSERT
                .replace("@", tableName)
                .replace("#", String.join(", ", columns))
                .replace("$", createPlaceholderString(columns.size()));
    }

    private CharSequence createPlaceholderString(int size) {
        String seq = "?";
        return seq.concat(new String(new char[size - 1]).replace("\0", ", ?"));
    }

    public String mount_update(String tableName, HashMap<String, Object> data, Integer numOfConditions) {
        return UPDATE
                .replace("@", tableName)
                .replace("#", generateColumnAndPlaceholderString(data.keySet()))
                .replace("$", new String(new char[numOfConditions]).replace("\0", "?"));
    }

    public String mount_select(String tableName) {
        return SIMPLE_SELECT
                .replace("@", "*")
                .replace("#", tableName);
    }

    public String mount_select(String tableName, HashMap<String, String> data) {
        return SELECT
                .replace("@", "*")
                .replace("#", tableName)
                .replace("$", generateColumnAndPlaceholderString(data.keySet()));
    }

    public String mount_select(String tableName, HashMap<String, String> data, ArrayList<String> columns) {
        return SELECT
                .replace("@", String.join(", ", (CharSequence) columns))
                .replace("#", tableName)
                .replace("$", generateColumnAndPlaceholderString(data.keySet()));
    }

    private String generateColumnAndPlaceholderString(Set<String> columns){
        List<String> pairs= new ArrayList<String>();

        for (String column : columns) {
            pairs.add(column + "=?");
        }

        return String.join(", ", pairs);
    }
}