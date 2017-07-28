package com.soul.learn.power.widget.roundimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;


public class GlideSuperRoundTransform extends BitmapTransformation {

	private static float radius = 0f;
	private static GlideSuperRoundTransform transformInstance;

	private GlideSuperRoundTransform(Context context) {
		this(context, 4);
	}

	public GlideSuperRoundTransform(Context context, int dp) {
		super(context);
		this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
	}
	
	public synchronized static GlideSuperRoundTransform getInstance(Context context)
	{
		if (transformInstance==null) {
			transformInstance = new GlideSuperRoundTransform(context);
		}
		return transformInstance;
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                               int outWidth, int outHeight) {
		return roundCrop(pool, toTransform);
	}

	private synchronized static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
		/*if (source == null)
			return null;
		int destWidth = AndroidApplication.androidApplication.superRoundBitmap.getWidth();
		int destHeight = AndroidApplication.androidApplication.superRoundBitmap.getHeight();
		
		Bitmap result = pool.get(destWidth, destHeight,
				Bitmap.Config.ARGB_8888); //ARGB_8888  RGB_565
		if (result == null) {
			result = Bitmap.createBitmap(destWidth, destHeight,
					Bitmap.Config.ARGB_8888);
		}
		
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
		Rect srcRect = new Rect(0, 0, source.getWidth(), source.getHeight());
		Rect dstRect = new Rect(0, 0, destWidth, destHeight);
		canvas.drawBitmap(source, srcRect, dstRect, paint);

		
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_ATOP));
		canvas.drawBitmap(AndroidApplication.androidApplication.superRoundBitmap, 0, 0, paint);

		
		return result;*/
		return null;
	}

	@Override
	public String getId() {
		return getClass().getName() + Math.round(radius);
	}
}
