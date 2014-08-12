package de.xonical.mvplayer.service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedQueue;


import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public abstract class GUIUpdater {
    private static  ConcurrentLinkedQueue<PropertyUpdater<?>>   dirtyPropertyUpdaters   =   new ConcurrentLinkedQueue<>();
    private static  Updater                                     updater                 =   new Updater();
    private static  boolean                                     isUpdating              =   false;

    public static <T> void bind(Property<T> property, ObservableValue<T> observable) {
        PropertyUpdater<T>  propertyUpdater = new PropertyUpdater<>(property, observable);
        observable.addListener(propertyUpdater);
    }

    public static <T> void unbind(Property<T> property, ObservableValue<T> observable) {
        PropertyUpdater<T>  tmpPropertyUpdater = new PropertyUpdater<>(property, observable);
        observable.removeListener(tmpPropertyUpdater);
    }

    private static synchronized void scheduleUpdate(PropertyUpdater<?> updater) {
        GUIUpdater.dirtyPropertyUpdaters.add(updater);

        if (!GUIUpdater.isUpdating) {
            GUIUpdater.isUpdating = true;
            Platform.runLater(GUIUpdater.updater);
        }
    }

    private static class PropertyUpdater<T> implements ChangeListener<T> {
        private boolean             isDirty     =   false;
        private Property<T>         property    =   null;
        private ObservableValue<T>  observable  =   null;

        public PropertyUpdater(Property<T> property, ObservableValue<T> observable) {
            this.property = property;
            this.observable = observable;
        }

        @Override
        /**
         * Called whenever the ObservableValue has changed. Marks this Updater as dirty.
         */
        public synchronized void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
            if (!this.isDirty) {
                this.isDirty = true;
                GUIUpdater.scheduleUpdate(this);
            }
        }

        /**
         * Updates the Property to the ObservableValue and marks it as clean again.
         *  Should only be called from the JavaFX thread.
         */
        public synchronized void update() {
            T value = this.observable.getValue();
            this.property.setValue(value);
            this.isDirty = false;
        }

        @Override
        /**
         * Two PropertyUpdaters are equals if their Property and ObservableValue map to the same object (address).
         */
        public boolean equals(Object otherObj) {
            PropertyUpdater<?>  otherUpdater = (PropertyUpdater<?>) otherObj;
            if (otherObj == null) {
                return false;
            } else {
                // Only compare addresses (comparing with equals also compares contents):
                return (this.property == otherUpdater.property) && (this.observable == otherUpdater.observable);
            }
        }
    }

    /**
     * Simple class containing the Runnable for the call to Platform.runLater.
     *  Hence, the run() method should only be called from the JavaFX thread.
     *
     */
    private static class Updater implements Runnable {

        @Override
        public void run() {
            synchronized (GUIUpdater.class) {

                // Loop through the individual PropertyUpdaters, updating them one by one:
                while(!GUIUpdater.dirtyPropertyUpdaters.isEmpty()) {
                    PropertyUpdater<?>  curUpdater = GUIUpdater.dirtyPropertyUpdaters.poll();
                    curUpdater.update();
                }

                // Mark as updated:
                GUIUpdater.isUpdating = false;
            }
        }

    }





}