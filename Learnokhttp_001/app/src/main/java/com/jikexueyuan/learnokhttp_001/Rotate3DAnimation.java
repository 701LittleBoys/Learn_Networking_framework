package com.jikexueyuan.learnokhttp_001;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 95262 on 2017/2/27.
 */

public class Rotate3DAnimation extends Animation {
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthz;
    private final float mReverse;
    private Camera mCamera;

    public Rotate3DAnimation(float mFromDegrees, float mToDegrees, float mCenterX, float mCenterY, float mDepthz, float mReverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthz = mDepthz;
        this.mReverse = mReverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);


        final float centerX = mCenterX;
        final float centerY = mCenterY;

        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();

        camera.save();
        if (mReverse == 1){
            camera.translate(0.0f,0.0f,0.0f);
        }
        else{
            camera.translate(0.0f,0.0f,mDepthz * (1.0f - interpolatedTime));
        }

        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);

    }
}
