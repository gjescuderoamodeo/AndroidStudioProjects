package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

import static com.davidkaluta.airhockey.GameActivity.deviceHeight;
import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

/**
 * The View for the game
 *
 * @author David Kaluta
 * @version 24
 * @since 1
 */
public class HockeyTable extends View {

    /**
     * A red paddle (PC)
     */
    RedPaddle rp;

    /**
     * A blue paddle (NPC)
     */
    BluePaddle bp;

    /**
     * A puck
     */
    Puck p;

    /**
     * The game background
     */
    Bitmap bg;

    /**
     * A separator line
     */
    Bitmap line;

    /**
     * A Paint to draw text with
     */
    Paint paint;

    /**
     * A timer for pausing
     */
    Timer timer;

    /**
     * The time required to pause
     */
    int pauseTime;

    /**
     * The game's difficulty
     */
    String difficulty;

    /**
     * Create a HockeyTable with a difficulty
     *
     * @param context    required for a View
     * @param difficulty The difficulty level
     */
    public HockeyTable(Context context, @NonNull String difficulty) {
        super(context);
        this.difficulty = difficulty;
        paint = new Paint();
        int deviceWidth = Resources.getSystem().getDisplayMetrics()
                .widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics()
                .heightPixels;
        pauseTime = -4;
        TimerTask timerTask = new TimeHelper();
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
        bg = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, deviceHeight, true);
        line = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, 20, true);
        rp = new RedPaddle(deviceWidth / 2, 7 * deviceHeight / 8,
                new Goal(deviceWidth / 4, 0, this), this);
        switch (difficulty) {
            case "2 Players":
                bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 0,
                        new Goal(deviceWidth / 4, deviceHeight - 10, this),
                        this);
                break;
            case "Easy":
                bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 0.5,
                        new Goal(deviceWidth / 4, deviceHeight - 10, this),
                        this);
                break;
            case "Medium":
            default:
                bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 1,
                        new Goal(deviceWidth / 4, deviceHeight - 10, this),
                        this);
                break;
            case "Hard":
                bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 2,
                        new Goal(deviceWidth / 4, deviceHeight - 10, this),
                        this);
                break;
            case "BRUTAL":
                bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 123,
                        new Goal(deviceWidth / 4, deviceHeight - 10, this),
                        this);
                break;
        }
        p = new Puck(deviceWidth / 2, deviceHeight / 2, this);
    }

    /**
     * Create a HockeyTable without a difficulty (will be Medium)
     *
     * @param context required for a View
     */
    public HockeyTable(Context context) {
        super(context);
        paint = new Paint();
        int deviceWidth = Resources.getSystem().getDisplayMetrics()
                .widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics()
                .heightPixels;
        bg = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, deviceHeight, true);
        line = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, 20, true);
        rp = new RedPaddle(deviceWidth / 2, 7 * deviceHeight / 8,
                new Goal(deviceWidth / 4, 0, this), this);
        bp = new BluePaddle(deviceWidth / 2, deviceHeight / 8, 1,
                new Goal(deviceWidth / 4, deviceHeight - 10, this), this);
        p = new Puck(deviceWidth / 2, deviceHeight / 2, this);
    }

    /**
     * Get the red paddle
     *
     * @return the red paddle
     */
    public RedPaddle getRP() {
        return rp;
    }

    /**
     * Get the puck
     *
     * @return the puck
     */
    public Puck getP() {
        return p;
    }

    /**
     * Get the blue paddle
     *
     * @return the blue paddle
     */
    public BluePaddle getBP() {
        return bp;
    }

    /**
     * Draw everything
     *
     * @param c required for onDraw
     */
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawBitmap(bg, 0, 0, null);
        c.drawBitmap(line, 0, deviceHeight / 2 - 10, null);
        rp.draw(c);
        rp.getGoal().draw(c);
        bp.getGoal().draw(c);
        bp.draw(c);
        p.draw(c);
        paint.setTextSize(144);
        paint.setColor(Color.WHITE);
        if (rp.isWinner())
            c.drawText(getContext().getString(R.string.red_victory),
                    200, deviceHeight / 2 - 100, paint);
        if (bp.isWinner())
            c.drawText(getContext().getString(R.string.blue_victory),
                    200, deviceHeight / 2 - 100, paint);
        if (TimeHelper.seconds - 3 < pauseTime)
            c.drawText(getContext().getString(R.string.goal),
                    200, deviceHeight / 2 - 100, paint);
        paint.setTextSize(50);
        c.drawText(Integer.toString(rp.getGoal().getScore()),
                10, deviceHeight - 100, paint);
        c.drawText(Integer.toString(bp.getGoal().getScore()),
                deviceWidth - 100, 50, paint);
        invalidate();
    }
}
