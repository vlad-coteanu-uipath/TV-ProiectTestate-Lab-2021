package main.utilities;

public class Result {

    private ResultType resultType;
    private boolean value;
    private String errorMessage;

    private Result() {};

    public ResultType getResultType() {
        return resultType;
    }

    public boolean getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static Result makeErrorResult(String errorMessage) {
        Result result = new Result();
        result.resultType = ResultType.EXCEPTION;
        result.value = false;
        result.errorMessage = errorMessage;
        return result;
    }

    public static Result makeNormalResult(boolean value) {
        Result result = new Result();
        result.resultType = ResultType.VALUE;
        result.value = value;
        result.errorMessage = null;
        return result;
    }

}
