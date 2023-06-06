package hwr.oop;

public enum State {
    TODO {
        @Override
        public State nextState() {
            return IN_PROGRESS;
        }

        @Override
        public State previousState() {
            return TODO;
        }

        @Override
        public State hold() {
            return TODO;
        }
    },
    IN_PROGRESS {
        @Override
        public State nextState() {
            return DONE;
        }

        @Override
        public State previousState() {
            return TODO;
        }

        @Override
        public State hold() {
            return ON_HOLD;
        }
    },
    ON_HOLD {
        @Override
        public State nextState() {
            return IN_PROGRESS;
        }

        @Override
        public State previousState() {
            return TODO;
        }

        @Override
        public State hold() {
            return ON_HOLD;
        }
    },
    DONE {
        @Override
        public State nextState() {
            return DONE;
        }

        @Override
        public State previousState() {
            return IN_PROGRESS;
        }

        @Override
        public State hold() {
            return DONE;
        }
    };

    public abstract State nextState();
    public abstract State previousState();
    public abstract State hold();

}