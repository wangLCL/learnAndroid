package com.wk.android.rate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.wk.android.constant.Constant;

public class RateUtils {
    private Activity context;
    private final ReviewManager manager;
    public RateUtils(Activity context){
        this.context = context;
        manager = ReviewManagerFactory.create(context);
    }

    public void rate() {
        try {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String referrer = "market://details?id=" + context.getPackageName();
                        intent.setData(Uri.parse(referrer));
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newRate() {
        final Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    //一个月只能显示一次
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow((Activity) context, reviewInfo);
                    flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                        }
                    });
                }
            }
        });
    }
}
