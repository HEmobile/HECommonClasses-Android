package br.com.hemobile.hecommomclasses_android.error;

/**
 * Created by hemobile on 17/10/14.
 */
public interface HEExceptionHandler {
    public void onUncaughtException(Throwable throwable, ErrorDetails details);
}