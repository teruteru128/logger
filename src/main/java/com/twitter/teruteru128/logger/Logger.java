package com.twitter.teruteru128.logger;

import java.util.Objects;


public class Logger {
    private static final String _LEVEL_FATAL = "SERVER";
    private static final String _LEVEL_WARN = "WARN";
    private static final String _LEVEL_ERROR = "ERROR";
    private static final String _LEVEL_INFO = "INFO";
    private static final String _LEVEL_DEBUG = "DEBUG";
    private static final String _LEVEL_TRACE = "TRACE";
    private static final int _LEVEL_LEVEL_FATAL = 6;
    private static final int _LEVEL_LEVEL_ERROR = 5;
    private static final int _LEVEL_LEVEL_WARN = 4;
    private static final int _LEVEL_LEVEL_INFO = 3;
    private static final int _LEVEL_LEVEL_DEBUG = 2;
    private static final int _LEVEL_LEVEL_TRACE = 1;
    private static final String _LOG_FORMAT_LEVEL_SET = " [%s] %s";
    private static String _LOG_LEVEL_STR;
    private static int _LOG_LEVEL_LEVEL_;
    private final java.util.logging.Logger _logger;

    public Logger(java.util.logging.Logger logger, String level) {

        // 初期化メソッド内で呼び出し想定。初回のみ、レベルセットを実施
        if (Objects.isNull(_LOG_LEVEL_STR)) {
            if (level.equals(_LEVEL_FATAL)) {
                _LOG_LEVEL_STR = _LEVEL_FATAL;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_FATAL;
            } else if (level.equals(_LEVEL_ERROR)) {
                _LOG_LEVEL_STR = _LEVEL_ERROR;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_ERROR;
            } else if (level.equals(_LEVEL_WARN)) {
                _LOG_LEVEL_STR = _LEVEL_WARN;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_WARN;
            } else if (level.equals(_LEVEL_DEBUG)) {
                _LOG_LEVEL_STR = _LEVEL_DEBUG;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_DEBUG;
            } else if (level.equals(_LEVEL_TRACE)) {
                _LOG_LEVEL_STR = _LEVEL_TRACE;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_TRACE;
            } else {
                _LOG_LEVEL_STR = _LEVEL_INFO;
                _LOG_LEVEL_LEVEL_ = _LEVEL_LEVEL_INFO;
            }
        }
        _logger = logger;
    }

    public void fatal(String msg) {
        if (_LEVEL_LEVEL_FATAL >= _LOG_LEVEL_LEVEL_) {
            _logger.severe(msg);
        }
    }

    public void error(String msg) {
        if (_LEVEL_LEVEL_ERROR >= _LOG_LEVEL_LEVEL_) {
            _logger.severe(msg);
        }
    }

    public void warn(String msg) {
        if (_LEVEL_LEVEL_WARN >= _LOG_LEVEL_LEVEL_) {
            _logger.warning(msg);
        }
    }

    public void info(String msg) {
        if (_LEVEL_LEVEL_INFO >= _LOG_LEVEL_LEVEL_) {
            _logger.info(msg);
        }
    }

    public void debug(String msg) {
        if (_LEVEL_LEVEL_DEBUG >= _LOG_LEVEL_LEVEL_) {
            // Debugレベルはマイクラで出してくれないので、INFOでだす
            _logger.info(_LOG_FORMAT_LEVEL_SET.formatted(_LEVEL_DEBUG, msg));
        }
    }

    public void trace(String msg) {
        if (_LEVEL_LEVEL_TRACE >= _LOG_LEVEL_LEVEL_) {
            // Traceレベルはマイクラで出してくれないので、INFOでだす
            _logger.info(_LOG_FORMAT_LEVEL_SET.formatted(_LEVEL_TRACE, msg));
        }
    }

    public java.util.logging.Logger getLogger() {
        return _logger;
    }
}
