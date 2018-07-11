package refactoring.test2;

public enum Orientation {
    NORTH(Orientation.EAST) {
        @Override
        public int[] getNewPosition(int[] oldPosition) {
            return new int[] { oldPosition[0], oldPosition[1] + 1 };
        }
    },
    WEST(Orientation.NORTH) {
        @Override
        public int[] getNewPosition(int[] oldPosition) {
            return new int[] { oldPosition[0] - 1, oldPosition[1] };
        }
    },
    SOUTH(Orientation.WEST) {
        @Override
        public int[] getNewPosition(int[] oldPosition) {
            return new int[] { oldPosition[0], oldPosition[1] - 1 };
        }
    },
    EAST(Orientation.SOUTH) {
        @Override
        public int[] getNewPosition(int[] oldPosition) {
            return new int[] { oldPosition[0] + 1, oldPosition[1] };
        }
    };

    private Orientation diractionToTurn;

    Orientation(Orientation diractionToTurn) {
        this.diractionToTurn = diractionToTurn;
    }

    public Orientation getDirectionToTurn() {
        return diractionToTurn;
    }

    public abstract int[] getNewPosition(int[] oldPosition);
}
