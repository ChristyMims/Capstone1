package com.example.myapplication;

// placeholder class for toggle
public class ToggleActivity{

    // dummy variable status (online/offline)
    private String statusU;
    private String statusL;

    // constructor
    public ToggleActivity(String status)
    {
        statusU = this.statusU;
        statusL = this.statusL;
    }

    // get
    public String getUberStatus()    { return statusU;  }
    // set
    public void setUberStatusU()    { statusU = this.statusU;  }

    // get
    public String getLyftStatus()    { return statusL;  }
    // set
    public void setLyftStatus(String statusL)    { statusL = this.statusL;  }


    // method for placeholder if-then to get toggle status for Uber
    public String findUberStatus() {

            // conditional
            if ("Uber" == "Uber")
                {  statusU = "Online";  }
            else
                {  statusU = "Offline";  }

        return statusU;
        }  // end findUberStatus

    // method for placeholder if-then to get toggle status for Uber
    public String findLyftStatus() {

        // conditional
        if ("Lyft" == "Lyf")
            {  statusL = "Online";  }
        else
            {  statusL = "Offline";  }

        return statusL;
    }  // end findLyftStatus

}
