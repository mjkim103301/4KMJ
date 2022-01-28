package com.hli.example.userbehavior;

public enum DayOfTheWeek {
    SUN(false) {
        @Override
        public DayOfTheWeek getNextDay() {
            return MON;
        }
    },
    MON(true)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return TUE;
        }
    },
    TUE(true)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return WED;
        }
    },
    WED(true)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return THU;
        }
    },
    THU(true)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return FRI;
        }
    },
    FRI(true)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return SAT;
        }
    },
    SAT(false)  {
        @Override
        public DayOfTheWeek getNextDay() {
            return SUN;
        }
    };

    private final boolean isWeekday;

    DayOfTheWeek(boolean isWeekday) {
        this.isWeekday = isWeekday;
    }

    public boolean isWeekday() {
        return isWeekday;
    }

    public abstract DayOfTheWeek getNextDay();
}
