package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceHeight;
import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

/**
 * The puck
 *
 * @author David Kaluta
 * @version 26
 * @since 1
 */
public class Puck extends RoundEntity implements Runnable {

    /**
     * The puck's velocity on the X-axis
     */
    private float dx;

    /**
     * The puck's velocity on the Y-axis
     */
    private float dy;

    /**
     * A HockeyTable to get the Paddles
     */
    private HockeyTable ht;

    /**
     * The puck's previous coordinates
     */
    private float[] prevCoords;

    /**
     * The previous coordinates of the red paddle
     */
    private float[] rpPrevCoords;

    /**
     * The previous coordinates of the blue paddle
     */
    private float[] bpPrevCoords;

    /**
     * If the game is in goal mode
     */
    private boolean goal;

    /**
     * Create a Puck
     *
     * @param x  X-position for the puck
     * @param y  Y-position for the puck
     * @param ht The HockeyTable
     */
    Puck(float x, float y, HockeyTable ht) {
        super(x, y, 0.5f,
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(ht.getResources(),
                                R.drawable.puck),
                        (int) ((deviceWidth)*64.0/1080.0), (int) ((deviceWidth)*64.0/1080.0), true));
        Thread thread = new Thread(this, "PuckThread");
        rpPrevCoords = new float[2];
        bpPrevCoords = new float[2];
        prevCoords = new float[2];
        goal = false;
        this.ht = ht;
        dx = 0;
        dy = 0;
        thread.start();
    }

    /**
     * Get if the puck is in goal mode
     *
     * @return the puck's goal status
     */
    public boolean isGoal() {
        return goal;
    }

    /**
     * Set the puck's goal mode
     *
     * @param goal the new goal mode
     */
    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    /**
     * The main loop for the puck's movement
     */
    @Override
    public void run() {
        while (!ht.getRP().isWinner() && !ht.getBP().isWinner()) {
            if (!goal) {
                if (x <= 0 || x >= deviceWidth - radius * 2)
                    dx = -dx;
                if (x < 0)
                    x = 0;
                if (x > deviceWidth - radius * 2)
                    x = deviceWidth - radius * 2;
                if (y <= 0 || y >= deviceHeight - radius * 2)
                    dy = -dy;
                RedPaddle rp = ht.getRP();
                if (rp != null) {
                    if (distanceFrom(rp) <= radius + rp.radius) {
                        while (distanceFrom(rp) <= radius + rp.radius) {
                            x -= dx;
                            centerPointX = x + radius;
                            y -= dy;
                            centerPointY = y + radius;
                        }
                        rp.delay = 2;
                        float m = dy / dx;
                        float[] rpCoords = {rp.getCenterPointX(), rp.getCenterPointY()};
                        // y - rpCoords[1] = m(x-rpCoords[0])
                        // y = mx - m*rpCoords[0] + rpCoords[1]
                        if (centerPointY < m * centerPointX - m * rpCoords[0] + rpCoords[1]) {
                            if (centerPointY > rp.centerPointY) {
                                dy = dy;
                                dx = -dx;
                            } else {
                                dy = -dy;
                                dx = dx;
                            }
                        } else if (centerPointY > m * centerPointX - m * rpCoords[0] + rpCoords[1]) {
                            if (centerPointY > rp.centerPointY) {
                                dx = dx;
                                dy = -dy;
                            } else {
                                dx = -dx;
                                dy = dy;
                            }
                        } else {
                            dx = -dx;
                            dy = -dy;
                        }
                    }
                }
                BluePaddle bp = ht.getBP();
                if (bp != null) {
                    if (distanceFrom(bp) <= radius + bp.radius) {
                        while (distanceFrom(bp) <= radius + bp.radius) {
                            x -= dx;
                            centerPointX = x + radius;
                            y -= dy;
                            centerPointY = y + radius;
                        }
                        bp.delay = 2;
                        float m = dy / dx;
                        float[] bpCoords = {bp.getCenterPointX(), bp.getCenterPointY()};
                        // y - rpCoords[1] = m(x-rpCoords[0])
                        // y = mx - m*rpCoords[0] + rpCoords[1]
                        if (centerPointY < m * centerPointX - m * bpCoords[0] + bpCoords[1]) {
                            if (centerPointY > bp.centerPointY) {
                                dy = dy;
                                dx = -dx;
                            } else {
                                dy = -dy;
                                dx = dx;
                            }
                        } else if (centerPointY > m * centerPointX - m * bpCoords[0] + bpCoords[1]) {
                            if (centerPointY > bp.centerPointY) {
                                dx = dx;
                                dy = -dy;
                            } else {
                                dx = -dx;
                                dy = dy;
                            }
                        } else {
                            dx = -dx;
                            dy = -dy;

                        }
                    }
                    if (rp.getGoal() != null && bp.getGoal() != null) {
                        if (x + radius * 2 > rp.getGoal().x
                                && x < rp.getGoal().x + deviceWidth / 2
                                && y < 10) {
                            rp.getGoal().incScore();
                            centerPointX = deviceWidth / 2;
                            x = centerPointX - radius;
                            centerPointY = deviceHeight / 2;
                            y = centerPointY - radius;
                            if (rp.getGoal().getScore() < 7) {
                                goal = true;
                                ht.pauseTime = TimeHelper.seconds;
                            } else {
                                rp.win();
                                Saver.setWins(ht.getContext(),
                                        Saver.getWins(ht.getContext()) + 1);
                                Saver.setWins(ht.getContext(),
                                        Saver.getWins(ht.getContext(), ht.difficulty) + 1,
                                        ht.difficulty);
                            }
                        } else if (x + radius * 2 > bp.getGoal().x
                                && x < bp.getGoal().x + deviceWidth / 2
                                && y + radius * 2 > deviceHeight - 10) {
                            bp.getGoal().incScore();
                            centerPointX = deviceWidth / 2;
                            x = centerPointX - radius;
                            centerPointY = deviceHeight / 2;
                            y = centerPointY - radius;
                            if (bp.getGoal().getScore() < 7) {
                                goal = true;
                                ht.pauseTime = TimeHelper.seconds;
                            } else {
                                bp.win();
                            }
                        }
                    }
                }
                if (dx == 0 && dy == 0) {
                    centerPointX = deviceWidth / 2;
                    x = centerPointX - radius;
                    centerPointY = deviceHeight / 2;
                    y = centerPointY - radius;
                    dy = 2;
                    dx = 2;
                }
                if (rp != null) {
                    rpPrevCoords[0] = rp.getX();
                    rpPrevCoords[1] = rp.getY();
                }
                if (bp != null) {
                    bpPrevCoords[0] = bp.getX();
                    bpPrevCoords[1] = bp.getY();
                }
                x += dx;
                centerPointX += dx;
                y += dy;
                centerPointY += dy;
                if (rp != null && bp != null) {
                    if (rp.delay != 0)
                        rp.delay--;
                    if (bp.delay != 0)
                        bp.delay--;
                }
            } else if (TimeHelper.seconds - 3 == ht.pauseTime) {
                goal = false;
                dy = 2;
                dx = 2;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
