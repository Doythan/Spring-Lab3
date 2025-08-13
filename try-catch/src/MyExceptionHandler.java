public class MyExceptionHandler {

    public String back(RuntimeException e) {
        // 로그 출력
        System.err.println("예외 발생: " + e.getMessage());

        // 브라우저로 반환할 HTML (간단한 alert + 이전 페이지로 이동)
        return "<script>"
                + "alert('" + escapeForJs(e.getMessage()) + "');"
                + "history.back();"
                + "</script>";
    }

    // XSS 방지를 위해 메시지를 JS 문자열로 안전하게 변환
    private String escapeForJs(String message) {
        if (message == null) return "";
        return message.replace("'", "\\'")
                .replace("\"", "\\\"");
    }
}
