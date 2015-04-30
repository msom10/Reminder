package msoma.reminder;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Mohanish on 14/04/15.
 * Sorting arraylist from http://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
 */
public  class Reminder implements Parcelable,Comparable<Reminder>  {

    private String tittle;
    private String desc;
    private Date dueDate;
    private boolean reminderStatus;

    // Static method to create Parcelable object (required)
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    public Reminder() {
    }

    public Reminder(String tittle, String desc, Date dueDate, boolean reminderStatus) {
        this.tittle = tittle;
        this.desc = desc;
        this.dueDate = dueDate;
        this.reminderStatus = reminderStatus;
    }

    public Reminder(Parcel in) {
        this.tittle = in.readString();
        this.desc = in.readString();
        this.dueDate = new Date(in.readLong());
        this.reminderStatus = in.readByte() != 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tittle);
        dest.writeString(desc);
        dest.writeLong(dueDate.getTime());
        dest.writeByte((byte)(reminderStatus?0:1));

    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReminderStatus() {
        return reminderStatus;
    }

    public void setReminderStatus(boolean reminderStatus) {
        this.reminderStatus = reminderStatus;
    }

    //http://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    @Override
    public int compareTo(Reminder another) {
        return getDueDate().compareTo(another.getDueDate());
    }
}
