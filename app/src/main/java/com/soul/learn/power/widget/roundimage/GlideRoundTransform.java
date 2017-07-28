package com.soul.learn.power.widget.roundimage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;


public class GlideRoundTransform extends BitmapTransformation {

	private static float radius = 0f;
	private static GlideRoundTransform transformInstance;

	public GlideRoundTransform(Context context) {
		this(context, 4);
	}

	public GlideRoundTransform(Context context, int dp) {
		super(context);
		this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
	}
	
	public synchronized static GlideRoundTransform getInstance(Context context)
	{
		if (transformInstance==null) {
			transformInstance = new GlideRoundTransform(context);
		}
		return transformInstance;
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                               int outWidth, int outHeight) {
		return roundCrop(pool, toTransform);
	}

	private synchronized static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
		if (source == null)
			return null;
		Bitmap result = pool.get(source.getWidth(), source.getHeight(),
				Bitmap.Config.ARGB_8888); //ARGB_8888  RGB_565
		if (result == null) {
			result = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					Bitmap.Config.ARGB_8888);
		}
		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP,
				BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
		
		canvas.drawRoundRect(rectF, radius, radius, paint);
//		canvas.drawBitmap(AndroidApplication.androidApplication.superRoundBitmap, 0, 0, paint);
		
		return result;
		
		/*if (source == null)
			return null;
		Bitmap result = pool.get(source.getWidth(), source.getHeight(),
				Bitmap.Config.RGB_565); //ARGB_8888  RGB_565
		if (result == null) {
			result = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					Bitmap.Config.RGB_565);
		}
		int width = source.getWidth();
		int height = source.getHeight();
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		paint.setColor(android.graphics.Color.WHITE);
		Bitmap clipped = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(clipped);
		canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(source, 0, 0, paint);
//		source.recycle();
		return clipped;*/
	}

	@Override
	public String getId() {
		return getClass().getName() + Math.round(radius);
	}
}
