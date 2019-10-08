void setup() {
  size(400, 400);
  background(255);
  noStroke();
  colorMode(HSB, 255, 100, 100);
  
  fill(0);
  
  // uses bit shifting to find RGB (https://processing.org/reference/red_.html) because it seems to be more accurate than the built-in functions
  // uses Math.round and Math.floor to convert floats in to integers
  // I only need Math.round to do that, but I was having issues with rounding before flooring
  color value = color(hue, saturation, brightness);
  text("HSB: " + Math.round(Math.floor(hue)) + ", " + Math.round(Math.floor(saturation(value))) + "%, " + Math.round(Math.floor(brightness(value))) + "%", (width/2-255/2), height/2-40);
  text("HEX: " + hex(value), (width/2-255/2), height/2-55);
  text("RGB: " + (value >> 16 & 0xFF) + ", " + (value >> 8 & 0xFF) + ", " + (value & 0xFF), (width/2-255/2), height/2-25);
};

// detects whether or not the mouse is within the boundaries of a rectangle
boolean ptInRect(float x, float y, float sizeX, float sizeY) {
  return (mouseX >= x && mouseX <= x + sizeX && mouseY >= y && mouseY <= y + sizeY);
};

// creates a gradient effect using 255 thin (1px) lines
void createGradient(float x, float y) {
  for (int i = 0; i < 255; i++) {
    fill(i, 255, 255);
    rect(i + x, y, 1, 40);
  };
};

// creates a gradient square in start position
void createGrid(float startX, float startY) {
  for (float y = 0; y <= 100; y++) {
    for (float x = 0; x <= 100; x++) {
      fill(hue, x, 100-y);
      square(startX + x, startY + y, 1);
    };
  };
};

float hue = 0;
float saturation = 100;
float brightness = 100;

float posX = 10;
float posY = 10;

void draw() {
  // calling set up to reset data
  setup();
  
  // preview rectangle
  fill(hue, saturation, brightness);
  rect(width/2-40, 40, 80, 20);
  
  // gradient grid and color selector bar
  createGrid(10, 10);
  createGradient(width/2-255/2, height/2-20);
  
  // position on grid
  fill(255);
  square(posX, posY, 3);
  
  // position on color selector
  fill(0);
  rect(hue + (width/2-255/2), height/2-20, 1, 40);
  
  if (mousePressed) {
    // if in the color selector
    if (ptInRect(width/2-255/2, height/2-20, 255, 40)) {
      hue = mouseX - (width/2-255/2);
    };
    // if in the grid
    if (ptInRect(10, 10, 100, 100)) {
      saturation = mouseX - 10;
      brightness = 110 - mouseY;
      posX = mouseX;
      posY = mouseY;
    };
  };
};
