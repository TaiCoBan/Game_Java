package com.btl.menu.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;

@Getter
public class Character {

//    STATE CONTROLLER
    static final int IDLE = 0;
    static final int RUN = 1;
    static final int JUMP = 2;
    static final int SPAWN = 3;
    static final int DEAD = 4;
    static final int LEFT = -1;
    static final int RIGHT = 1;
    static final float GRAVITY = 0f;
    static final float ACCELERATION = 20f;
    static final float MAX_VEL = 6f;
    static final float FRICTION = 0.1f;
    static final float JUMP_FORCE = 6f;


//    character components
    private Texture characterTexture;
    Vector2 pos = new Vector2();
    Vector2 vel = new Vector2();            //control character movement
    Vector2 accel = new Vector2();          //character acceleration
    Rectangle body = new Rectangle();       //use for collision detect

    int currentState;
    boolean grounded = false;

    public Character(float x, float y){
        pos.x = x;
        pos.y = y;
        body.height = 0.8f;
        body.width = 0.6f;
        currentState = SPAWN;
        characterTexture = new Texture("Temp Asset/gunman.png");
    }

    public void update(float deltaTime){
        inputHandling();

        accel.y = -GRAVITY;                         //character affected by gravity to fall down after jumping
        accel.scl(deltaTime);                       //Scale acceleration with deltaTime
        vel.add(accel);
        if(accel.x == 0) vel.x *= FRICTION;         //Rest friction

        //limit moving speed
        if(vel.x > MAX_VEL) vel.x = MAX_VEL;
        if(vel.x < -MAX_VEL) vel.x = -MAX_VEL;
        moveCharacter();
        vel.scl(1f / deltaTime);              //turn velocity back to time-independent form


    }
    void moveCharacter(){
        body.x += vel.x;
        body.y += vel.y;

        pos.x = body.x;
        pos.y = body.y;
    }

    void inputHandling(){
        if(Gdx.input.isKeyPressed(Input.Keys.W) && currentState != JUMP)
        {
            vel.y = JUMP_FORCE;
            currentState = JUMP;
            grounded = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (currentState != JUMP) currentState = RUN;
            accel.x = ACCELERATION * LEFT;
            System.out.println("A Pressed" + accel.x);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (currentState != JUMP) currentState = RUN;
            accel.x = ACCELERATION * RIGHT;
            System.out.println("D Pressed" + accel.x);
        } else {
            if (currentState != JUMP) currentState = IDLE;
            accel.x = 0;
            vel.x = 0;                                          //temporary cause character cant auto stop
            System.out.println("Nothing Pressed" + accel.x);
        }
    }


}
