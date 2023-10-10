module com.example.serversd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires jjwt.api;
    requires jjwt.impl;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens com.sd.view.modal to javafx.fxml;
    opens com.sd.view to javafx.fxml;
    opens com.sd.server.models to org.hibernate.orm.core;
    opens com.sd.server.exceptions;
    opens com.sd.server;

    exports com.sd.view.modal;
    exports com.sd.server.packages to com.fasterxml.jackson.databind;
    exports com.sd.server.base to com.fasterxml.jackson.databind;
    exports com.sd.server.package_data.request.login to com.fasterxml.jackson.databind;
    exports com.sd.server.package_data.response.login to com.fasterxml.jackson.databind;
    exports com.sd.server.packages.login to com.fasterxml.jackson.databind;
    exports com.sd.server.package_data.request.user to com.fasterxml.jackson.databind;
    exports com.sd.server.packages.user to com.fasterxml.jackson.databind;
    exports com.sd.server.package_data.request.logout to com.fasterxml.jackson.databind;
    exports com.sd.server.packages.logout to com.fasterxml.jackson.databind;
    exports com.sd.view;
}
