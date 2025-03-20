package io.github.some_example_name.movement;


// Manage all manual movements from the user, listens to keyboard input and calls manual movement functions accordingly

import io.github.some_example_name.entities.Character;
import io.github.some_example_name.entities.Objects;
import io.github.some_example_name.inputoutput.KeyboardInput;

public class ManualMovement {
    KeyboardInput keyboardInput = new KeyboardInput();

    //Manual Movement for Objects
    public void moveObj(Objects object)
    {
        if (keyboardInput.isMovementKeyPressed()) //Change to input from input output manager
        {
            if(keyboardInput.getLastPressedKeyName().equals("A")) {
                object.moveLeft();
            }
            if (keyboardInput.getLastPressedKeyName().equals("D")) {
                object.moveRight();
            }
            if (keyboardInput.getLastPressedKeyName().equals("Space")){
                object.jump();
            }
        }
    }
    //Manual Movement for Characters
    public void moveChar(Character character) {
        if (keyboardInput.isMovementKeyPressed())
        {
            if (keyboardInput.getLastPressedKeyName().matches("Left")) {
                character.moveLeft();
            }
            if (keyboardInput.getLastPressedKeyName().matches("Right")) {
                character.moveRight();
            }
            if (keyboardInput.getLastPressedKeyName().matches("Space")) {
                character.jump();
            }
        }
    }



}
