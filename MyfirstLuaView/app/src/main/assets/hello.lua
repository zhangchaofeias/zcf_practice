
window.backgroundColor(0xDDDDDD);

label = Label();
label.frame(0,50,200, 60);
label.text("Hello World LuaView to Android");

button = Button();
button.title("Alert");
button.callback(
        function()
          Alert("button test!!")
          end
          );

button1 = Button();
button1.title("Toast")
button1.align(Align.RIGHT)
button1.callback(
        function()
        Toast("test toast")
        end
        );