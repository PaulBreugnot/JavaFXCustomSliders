package controls.colorSlider.simpleColorSlider;

import controls.Slider;
import controls.colorSlider.ColorBar;
import controls.colorSlider.ColorConfig;
import controls.colorSlider.ColorSelectionMode;
import controls.colorSlider.ColorSlider;
import controls.simpleSlider.SimpleBar;
import controls.simpleSlider.SimpleSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class SimpleColorBar extends SimpleBar implements ColorBar {
	
	public SimpleColorBar(Slider slider) {
		super(slider);
	}

	@Override
	public void setColors(double value1, double value2) {
		// bar1.setFill(Color.RED);
		bar1.setFill(ColorBar.subLinearGradient(value1, value2, (ColorSlider) slider));
	}

	@Override
	public void linkColorListeners() {
		((SimpleSlider) slider).valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				((SimpleSlider) slider).getSliderSkin().getMidCursor().setFill(
						resolveCursorColor(
								(double) newValue,
								((ColorSlider) slider).getColorSelectionMode(),
								((ColorSlider) slider).getColorConfig()
								));
			}
		});
		
	}
	
	public static Color resolveCursorColor(double value, ColorSelectionMode colorSelectionMode, ColorConfig colorConfig) {
		switch (colorSelectionMode) {
		case HUE:
			return Color.hsb(value, colorConfig.getSaturation(), colorConfig.getbrightness());
		case SATURATION:
			return Color.hsb(colorConfig.getHue(), value, colorConfig.getbrightness());
		case BRIGHTNESS:
			return Color.hsb(colorConfig.getHue(), colorConfig.getSaturation(), value);
		default:
			return null;
		}
	}

}
