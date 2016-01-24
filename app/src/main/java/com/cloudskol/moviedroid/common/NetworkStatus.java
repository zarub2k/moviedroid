package com.cloudskol.moviedroid.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.cloudskol.moviedroid.common.NetworkStatusConstant.*;

/**
 * @author tham
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({OK, SERVER_DOWN, OFFLINE})
public @interface NetworkStatus {}
