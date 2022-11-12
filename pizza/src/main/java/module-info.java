module com.asu.edu.cse360.group2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.asu.edu.cse360.group2 to javafx.fxml;
    opens com.asu.edu.cse360.group2.Chef to javafx.fxml;
    opens com.asu.edu.cse360.group2.Customer to javafx.fxml;
    opens com.asu.edu.cse360.group2.Delivery to javafx.fxml;
    opens com.asu.edu.cse360.group2.Admin to javafx.fxml;

    exports com.asu.edu.cse360.group2;
    exports com.asu.edu.cse360.group2.Chef;
    exports com.asu.edu.cse360.group2.Customer;
    exports com.asu.edu.cse360.group2.Delivery;
    exports com.asu.edu.cse360.group2.Admin;
}
