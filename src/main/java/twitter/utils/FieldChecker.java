package twitter.utils;

public final class FieldChecker {
    public static boolean isAllFilled(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty() || field.length() == 0)
                return false;
        }

        return true;
    }
}
