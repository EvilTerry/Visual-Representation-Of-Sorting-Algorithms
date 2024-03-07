import java.awt.*;

public class Bar {
    private int value;
    private Color color;
    private Color selectedColor;

    public Bar(int value)
    {
        this.value = value;
        this.color = Color.WHITE;
        this.selectedColor = Color.YELLOW;
    }
    public int getValue()
    {
        return value;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void toggleSelect()
    {
        Color temp = color;
        color = selectedColor;
        selectedColor = temp;
    }
}