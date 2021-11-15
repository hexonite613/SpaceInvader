package screen;

import engine.Cooldown;
import engine.Core;
import engine.GameState;
import engine.Score;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class SettingScreen extends Screen{


    /** Milliseconds between changes in user selection. */
    private static final int SELECTION_TIME = 200;

    /** Time between changes in user selection. */
    private final Cooldown selectionCooldown;


    public SettingScreen(final int width, final int height, final int fps) {
        super(width, height, fps);

        this.returnCode=1;

        this.settingCode = 1;
        this.selectionCooldown = Core.getCooldown(SELECTION_TIME);
        this.selectionCooldown.reset();
    }

    public final int run() {
        super.run();

        return this.returnCode;
    }

    protected final void update() {
        super.update();

        draw();

        /**고르기*/
        if (this.selectionCooldown.checkFinished()
                && this.inputDelay.checkFinished()) {
            if (inputManager.isKeyDown(KeyEvent.VK_UP)
                    || inputManager.isKeyDown(KeyEvent.VK_W)) {
                previousMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_DOWN)
                    || inputManager.isKeyDown(KeyEvent.VK_S)) {
                nextMenuItem();
                this.selectionCooldown.reset();
            }
            if (inputManager.isKeyDown(KeyEvent.VK_ESCAPE)||inputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                this.isRunning = false;
            }
        }
    }

    /**다음 선택지*/
    private void nextMenuItem() {
        if(this.settingCode==3)
            this.settingCode=1;
        else
            settingCode++;
    }

    /**그전 선택지*/
    private void previousMenuItem() {
        if (this.settingCode == 1)
            this.settingCode = 3;
        else
            settingCode--;
    }






    private void draw(){
        /**화면 초기화*/
        drawManager.initDrawing(this);

        /**그리기*/
        drawManager.drawSettingScreen(this);
        drawManager.drawSettingmenu(this, this.settingCode);


        /**다 그렸다고 업데이트*/
        drawManager.completeDrawing(this);
    }

    /**여기에서 settingcode에 따라 값 배정*/


    @Override
    public int updatelevel(){
        return this.settingCode;
    }


}

