package com.cloudskol.moviedroid.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.cloudskol.moviedroid.common.NetworkStatusConstant.*;

/**
 * @author tham
 *
 * Network status annotation which will indicate all the available status for the application
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({OK, SERVER_DOWN, OFFLINE})
public @interface NetworkStatus {}
