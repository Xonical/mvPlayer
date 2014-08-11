package com.airhacks.followme.presentation.followme;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import javax.inject.Inject;

import com.airhacks.followme.business.flightcontrol.boundary.Tower;

/**
 *
 * @author adam-bien.com
 */
public class FollowmePresenter implements Initializable {

	@FXML
	Label message;

	@FXML
	Button fillListViewButton;

	@FXML
	ListView listViewDirectories;

	@Inject
	Tower tower;

	@Inject
	private String prefix;

	@Inject
	private String happyEnding;

	@Inject
	private LocalDate date;

	private String theVeryEnd;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// fetched from followme.properties
		this.theVeryEnd = rb.getString("theEnd");
        prepareMyList();
	}

	public void launch() {
		message.setText("Date: " + date + " -> " + prefix
				+ tower.readyToTakeoff() + happyEnding + theVeryEnd);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void handleListView(ActionEvent event) {
		System.out.println("vfsadfsadgfsadg");




//		List<String> strList = new ArrayList<String>();
//		strList.add("Foo-Ordner");
//		strList.add("33 Dateien");
		//ListView<MyObject> listView = new ListView<>();
		ObservableList<MyObject> myObservableList = FXCollections.observableList(myList);

		listViewDirectories.setItems(myObservableList);

		listViewDirectories.setCellFactory(new Callback<ListView<MyObject>, ListCell<MyObject>>(){

            @Override
            public ListCell<MyObject> call(ListView<MyObject> p) {

                ListCell<MyObject> cell = new ListCell<MyObject>(){

                    @Override
                    protected void updateItem(MyObject t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getDay() + ":" + t.getNumber());
                        }
                    }

                };

                return cell;
            }
        });
	}


    class MyObject{
        String day;
        int number;

        MyObject(String d, int n){
            day = d;
            number = n;
        }

        String getDay(){
            return day;
        }

        int getNumber(){
            return number;
        }
    }


    List<MyObject> myList;

    //Create dummy list of MyObject
    private void prepareMyList(){
        myList = new ArrayList<>();
        myList.add(new MyObject("Sunday", 50));
        myList.add(new MyObject("Monday", 60));
        myList.add(new MyObject("Tuesday", 20));
        myList.add(new MyObject("Wednesday", 90));
        myList.add(new MyObject("Thursday", 30));
        myList.add(new MyObject("Friday", 62));
        myList.add(new MyObject("Saturday", 65));
    }


}



