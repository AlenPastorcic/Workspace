package com.techelevator;

public class Television {

        // Instance variables
        private boolean isOn;
        private int currentChannel;
        private int currentVolume;

        // Constructor
        public Television() {
            isOn = false; // TV is off by default
            currentChannel = 3;
            currentVolume = 2;
        }

        // Methods
        public void turnOff() {
            isOn = false;
        }

        public void turnOn() {
            isOn = true;
            currentChannel = 3; // Reset channel to 3 when turning on
            currentVolume = 2; // Reset volume to 2 when turning on
        }

        public void changeChannel(int newChannel) {
            if (isOn && newChannel >= 3 && newChannel <= 18) {
                currentChannel = newChannel;
            }
        }

        public void channelUp() {
            if (isOn) {
                currentChannel++;
                if (currentChannel > 18) {
                    currentChannel = 3; // Wrap around to channel 3
                }
            }
        }

        public void channelDown() {
            if (isOn) {
                currentChannel--;
                if (currentChannel < 3) {
                    currentChannel = 18; // Wrap around to channel 18
                }
            }
        }

        public void raiseVolume() {
            if (isOn && currentVolume < 10) {
                currentVolume++;
            }
        }

        public void lowerVolume() {
            if (isOn && currentVolume > 0) {
                currentVolume--;
            }
        }

        // Getters and Setters (optional)
        public boolean isOn() {
            return isOn;
        }

        public int getCurrentChannel() {
            return currentChannel;
        }

        public int getCurrentVolume() {
            return currentVolume;
        }

        public void setCurrentChannel(int currentChannel) {


        }
    }
