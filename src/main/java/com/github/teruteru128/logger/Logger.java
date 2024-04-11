package com.github.teruteru128.logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.bukkit.plugin.Plugin;

/**
 * プラグイン用ロガークラス
 * これを経由することで、プラグイン本体のconfig.ymlにレベルを書き込むと、レベルの操作が可能になる。
 * */
public class Logger {

  /** プラグインごとのLoggerが入ってるMAP */
  private static final Map<Plugin, Logger> map = Collections.synchronizedMap(new HashMap<>());
  /** ログ出力本体 */
  private final java.util.logging.Logger _logger;
  /** ログレベル */
  private final Level configuredLevel;

  /**
   * コンストラクタ（文字列のログレベルを変更して、Level型のコンストラクタに渡す）
   * @param logger ロガー
   * @param level ログレベル（文字列）
   * */
  Logger(java.util.logging.Logger logger, String level) {
    this(logger, Level.toLevel(level, Level.INFO));
  }

  /**
   * コンストラクタ
   * @param logger ロガー
   * @param level ログレベル
   * */
  Logger(java.util.logging.Logger logger, Level level) {
    this._logger = logger;
    this.configuredLevel = level;
  }

  /**
   * ロガー書き込み
   * */
  public static void register(Plugin plugin, String logLevel) {
    map.put(plugin, new Logger(plugin.getLogger(), logLevel));
  }

  /**
   * プラグインに紐づくロガーを取得する
   * @param plugin プラグイン本体
   * */
  public static Logger getInstance(Plugin plugin) {
    return map.get(plugin);
  }

  /**
   * プラグインに紐づくロガーを削除する
   * @param plugin プラグイン本体
   * */
  public static void unregister(Plugin plugin) {
    map.remove(plugin);
  }

  /**
   * fatalレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void fatal(String msg) {
    this.log(Level.FATAL, java.util.logging.Level.SEVERE, msg);
  }

  /**
   * errorレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void error(String msg) {
    this.log(Level.ERROR, java.util.logging.Level.SEVERE, msg);
  }

  /**
   * warnレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void warn(String msg) {
    this.log(Level.WARN, java.util.logging.Level.WARNING, msg);
  }

  /**
   * infoレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void info(String msg) {
    this.log(Level.INFO, java.util.logging.Level.INFO, msg);
  }

  /**
   * debugレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void debug(String msg) {
    this.log(Level.DEBUG, java.util.logging.Level.INFO, msg);
  }

  /**
   * traceレベルのログを出力する
   * @param msg 出力メッセージ
   * */
  public void trace(String msg) {
    this.log(Level.TRACE, java.util.logging.Level.INFO, msg);
  }

  /**
   * ログの出力を判断して、を出力する
   * @param viewLevel 表示に使用するログLevel
   * @param logLevel 実際に出力に使用するログLevel
   * @param msg 出力メッセージ
   * */
  public void log(org.apache.logging.log4j.Level viewLevel, java.util.logging.Level logLevel,
      String msg) {
    if (viewLevel.compareTo(this.configuredLevel) <= 0) {
      this._logger.log(logLevel, msg);
    }
  }

  /**
   * 格納されているロガーを取得する
   * */
  public java.util.logging.Logger getLogger() {
    return this._logger;
  }
}
