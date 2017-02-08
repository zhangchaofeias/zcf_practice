btn = Button();
btn.title(bridge.getTextString());
btn.callback(function()
    bridge.showLuaData("data from lua")
end)