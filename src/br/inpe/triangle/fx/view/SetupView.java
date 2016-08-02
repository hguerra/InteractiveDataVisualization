package br.inpe.triangle.fx.view;

import javafx.scene.control.Button;

/**
 * @author Heitor
 * @since 05/05/2016
 */
public enum SetupView implements PaneStyle {
    BASIC {
        private Button button;
        private String style = "btnGlobe_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }

    }, LAYER {
        private Button button;
        private String style = "btnLayer_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }


    }, DATABASE {
        private Button button;
        private String style = "btnDataBase_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            if (button == null) return;
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button != null && button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }


    }, KINECT {
        private Button button;
        private String style = "btnKinect_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }


    }, LAYER_COLOR {
        private Button button;
        private String style = "btnLayerColor_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            if (button == null) return;
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button != null && button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }


    }, STYLE_DATA {
        private Button button;
        private String style = "btnStyleData_selected";

        @Override
        public void setButton(Button btn) {
            this.button = btn;
        }

        @Override
        public void addButtonStyle() {
            if (button == null) return;
            button.getStyleClass().add(style);
        }

        @Override
        public void removeButtonStyle() {
            if (button != null && button.getStyleClass().contains(style))
                button.getStyleClass().remove(style);
        }

    };

    public void clearButtonStyle() {
        for (SetupView view : SetupView.values()) {
            view.removeButtonStyle();
        }
    }
}
