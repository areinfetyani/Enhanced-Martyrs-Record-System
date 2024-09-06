import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	static DoublyLinkedList doublylist = new DoublyLinkedList();
	Stage locationStage, martyrsStage, statStage, saveStage, viewStage;
	TextField selecttf, selecttxt, tf3, intf1, intf2, intf3, intf4, searchtxt;
	Label found, select;
	Button showStatistics;

	@Override
	public void start(Stage primaryStage) {
		Font font = Font.font("Comic Sans MS", FontWeight.MEDIUM, FontPosture.REGULAR, 17);
		Image img = new Image("martyrs.jpg");
		ImageView iv = new ImageView(img);
		Image imglogo = new Image("logo.png");
		ImageView ivlogo = new ImageView(imglogo);
		ivlogo.setFitWidth(75);
		ivlogo.setFitHeight(70);
		iv.setFitWidth(650);
		iv.setFitHeight(500);
		Label logo = new Label("The Israeli Information Center for Human Rights in the Occupied Territories");
		logo.setWrapText(true);
		logo.setPrefWidth(250);
		logo.setGraphic(ivlogo);
		logo.setStyle("-fx-text-fill:WHITE;");
		logo.setPrefHeight(120);
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color:BLACK;");
		Button btfile = new Button("Load Martyrs File");
		Button location = new Button("Location screen");
		Button martyrs = new Button("Martyrs Screen");
		Button stat = new Button("Statistics Screen");
		Button save = new Button("Save Screen");
		btfile.setFocusTraversable(false);
		btfile.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		btfile.setPrefWidth(240);
		btfile.setFont(font);
		btfile.setOnMouseEntered(e -> {
			btfile.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		btfile.setOnMouseExited(e -> {
			btfile.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		btfile.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(primaryStage);
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts[2] == "") {
						parts[2] = "Unknown Location";
					}
					doublylist.add(parts[2]);
				}
			} catch (IOException ex) {
				System.out.println("Error");
				ex.printStackTrace();
			}
			doublylist.printList();
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {
				br.readLine();
				String line;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",", -1);
					if (parts[1] == "") {
						parts[1] = "0";
					}
					if (parts[0] == "") {
						parts[0] = "Name unknown to B'Tselem";
					}
					String[] datedetails = parts[3].split("/", -1);
					if (parts[3] == "" || !parts[3].contains("/") || datedetails.length != 3) {
						parts[3] = "1/1/1990";
					}
					if (datedetails.length == 3) {
						if (datedetails[0] == "" || datedetails[1] == "" || datedetails[2] == "") {
							parts[3] = "1/1/1990";
						}
						if (Integer.parseInt(datedetails[0]) == 2 && (Integer.parseInt(datedetails[1]) > 28)) {
							parts[3] = "2/28/2023";
						}
					}
					if (parts[4] == "") {
						parts[4] = "NA";
					}
					if (parts[2] == "") {
						parts[2] = "Unknown Location";
					}
					String name = parts[0];
					int age = Integer.parseInt(parts[1]);
					String city = parts[2];
					Date date = new Date(parts[3]);
					Character gender = parts[4].charAt(0);
					Martyr obj = new Martyr(name, age, date, gender);
					DateStack ds = new DateStack(obj.getDateOfDeath());
					doublylist.getNode(city).getRecord().setNames(doublylist.getNode(city).getRecord().getNames(), obj);
					doublylist.getNode(city).getRecord().setDates(doublylist.getNode(city).getRecord().getDates(), ds);
					if (((DateStack) (doublylist.getNode(city).getRecord().getDates().findNode(ds).getData())).getDate()
					        .equals(obj.getDateOfDeath())) {
						((DateStack) (doublylist.getNode(city).getRecord().getDates().findNode(ds).getData()))
						        .setStack(obj);
					}

				}
			} catch (IOException ex) {
				System.out.println("Error");// if there's a problem with the chosen file, show an exception
				ex.printStackTrace();
			}
//			DateStack d = new DateStack(new Date("5/7/2021"));
//			((DateStack) (doublylist.getNode("Nablus").getRecord().getDates().findNode(d).getData())).getStack()
//			        .printStack();
		});
		location.setFocusTraversable(false);
		location.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		location.setPrefWidth(240);
		location.setFont(font);
		location.setOnMouseEntered(e -> {
			location.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		location.setOnMouseExited(e -> {
			location.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		location.setOnAction(e -> {
			locationStage = new Stage();
			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(10, 10, 10, 10));
			GridPane gp = new GridPane();
			Label insert = new Label("Insert new location record:");
			Label update = new Label("Update or delete a location record:");
			Label search = new Label("Search for a location record:");
			TextField tf1 = new TextField();
			TextField tf2 = new TextField();
			tf3 = new TextField();
			TextField updtf = new TextField();
			Button in = new Button("Insert");
			in.setOnAction(a -> {
				if (tf1.getText() != "")
					doublylist.add(tf1.getText());
				doublylist.printList();
			});
			Label msg = new Label("");
			HBox updatefield = new HBox(5);
			Button upd = new Button("Update");
			Button dlt = new Button("Delete");
			dlt.setOnAction(a -> {
				if (tf2.getText() != "" && doublylist.getNode(tf2.getText()) != null) {
					msg.setText("city " + tf2.getText() + " found.");
					Stage sure = new Stage();
					Label lbl = new Label(
					        "This record you're deleting has information, are you sure you want to delete it?");
					lbl.setWrapText(true);
					lbl.setFont(font);
					lbl.setStyle("-fx-text-alignment: center;");

					Button no = new Button("No");
					no.setOnAction(z -> {
						sure.close();
						msg.setText("city " + tf2.getText() + " is not deleted.");
						doublylist.printList();
					});
					Button yes = new Button("Yes");
					yes.setOnAction(z -> {
						doublylist.remove(tf2.getText());
						msg.setText("city " + tf2.getText() + " deleted.");
						sure.close();
						doublylist.printList();
					});
					HBox yesno = new HBox(5);
					yesno.getChildren().addAll(no, yes);
					yesno.setAlignment(Pos.CENTER);
					VBox noyes = new VBox(5);
					noyes.setAlignment(Pos.CENTER);
					noyes.getChildren().addAll(lbl, yesno);
					StackPane p = new StackPane();
					p.getChildren().add(noyes);
					p.setPadding(new Insets(5, 5, 5, 5));
					Scene s = new Scene(p, 300, 200);
					sure.setScene(s);
					sure.setTitle("Warning");
					sure.show();
				} else {
					msg.setText("city " + tf2.getText() + " not in list.");
				}

			});

			upd.setOnAction(a -> {
				if (tf2.getText() != "" && updtf.getText() != "" && doublylist.getNode(tf2.getText()) != null) {

					doublylist.add(updtf.getText());
					Record old = doublylist.getNode(tf2.getText()).getRecord();
					AVLTree old1 = old.getNames();
					AVLTree2 old2 = old.getDates();
					doublylist.getNode(updtf.getText()).getRecord().setNames(old1);
					doublylist.getNode(updtf.getText()).getRecord().setDates(old2);
					doublylist.remove(tf2.getText());
					msg.setText("city " + tf2.getText() + " updated.");
					System.out.println("start******************");
					System.out.println(doublylist.getNode(updtf.getText()).getRecord().getNames().toString());
					System.out.println(doublylist.getNode(updtf.getText()).getRecord().getDates().toString());
					System.out.println(doublylist.getNode(updtf.getText()).getRecord().getLocation());
					System.out.println("end************************");
					doublylist.printList();
				} else {
					msg.setText("city " + tf2.getText() + " not in list.");
				}

			});
			Button srch = new Button("Search");
			found = new Label("");
			srch.setOnAction(a -> {
				if (tf3.getText() != "" && doublylist.getNode(tf3.getText()) != null) {

					found.setText("city found");
					martyrs.fire();
					select.setText(tf3.getText());

				} else {
					found.setText("city not found");
				}
				stat.setDisable(false);
			});
			updatefield.getChildren().addAll(upd, updtf);

			Image im = new Image("map.jpg");
			ImageView imv = new ImageView(im);
			imv.setFitWidth(600);
			imv.setFitHeight(300);
			gp.setVgap(5);
			gp.setHgap(50);
			vbox.getChildren().addAll(gp, imv);
			vbox.setAlignment(Pos.CENTER);
			gp.setAlignment(Pos.TOP_CENTER);
			gp.add(insert, 0, 0);
			gp.add(tf1, 0, 1);
			gp.add(in, 0, 2);
			gp.add(update, 1, 0);
			gp.add(tf2, 1, 1);
			gp.add(updatefield, 1, 2);
			gp.add(dlt, 1, 3);
			gp.add(msg, 1, 4);
			gp.add(search, 2, 0);
			gp.add(tf3, 2, 1);
			gp.add(srch, 2, 2);
			gp.add(found, 2, 3);
			Scene scene = new Scene(vbox, 800, 550);
			locationStage.setScene(scene);
			locationStage.show();

		});
		martyrs.setFocusTraversable(false);
		martyrs.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		martyrs.setPrefWidth(240);
		martyrs.setFont(font);
		martyrs.setOnMouseEntered(e -> {
			martyrs.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		martyrs.setOnMouseExited(e -> {
			martyrs.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		martyrs.setOnAction(e -> {
			martyrsStage = new Stage();
			select = new Label();
			Label name = new Label("Name: ");
			Label age = new Label("Age: ");
			Label date = new Label("Date of Death: ");
			Label gender = new Label("Gender: ");
			Label name2 = new Label("Name: ");
			Label age2 = new Label("Age: ");
			Label date2 = new Label("Date of Death: ");
			Label gender2 = new Label("Gender: ");
			Label in = new Label("Insert new Martyr: ");
			Label msg = new Label("");
			intf1 = new TextField();
			intf2 = new TextField();
			intf3 = new TextField();
			intf4 = new TextField();
			Button load = new Button("Load Table content");
			Button insertmar = new Button("Insert");
			TableView<Martyr> table = new TableView<>();
			ObservableList<Martyr> data = FXCollections.observableArrayList();
			table.setItems(data);
			TableColumn Col1 = new TableColumn("Name");
			Col1.setMinWidth(100);
			Col1.setCellValueFactory(new PropertyValueFactory<Martyr, String>("name"));
			TableColumn Col2 = new TableColumn("Age");
			Col2.setMinWidth(100);
			Col2.setCellValueFactory(new PropertyValueFactory<Martyr, Integer>("age"));
			TableColumn Col3 = new TableColumn("Date of death");
			Col3.setMinWidth(130);
			Col3.setCellValueFactory(new PropertyValueFactory<Martyr, Date>("dateOfDeath"));
			TableColumn Col4 = new TableColumn("Gender");
			Col4.setMinWidth(100);
			Col4.setCellValueFactory(new PropertyValueFactory<Martyr, Character>("gender"));
			table.getColumns().addAll(Col1, Col2, Col3, Col4);
			load.setOnAction(a -> {
				data.clear();
				addDataToTable(doublylist.getNode(select.getText()).getRecord().getNames().getRoot(), data);

			});

			insertmar.setOnAction(a -> {
				if (select.getText() != "" && intf1.getText() != "" && intf2.getText() != "" && intf3.getText() != ""
				        && intf4.getText() != "") {
					if (doublylist.getNode(select.getText()) != null) {
						String marName = intf1.getText();
						int marAge = Integer.parseInt(intf2.getText());
						Date marDateOfDeath = new Date(intf3.getText());
						char marGender = intf4.getText().charAt(0);
						Martyr m = new Martyr(marName, marAge, marDateOfDeath, marGender);
						DateStack d = new DateStack(marDateOfDeath);
						doublylist.getNode(select.getText()).getRecord()
						        .setNames(doublylist.getNode(select.getText()).getRecord().getNames(), m);
						doublylist.getNode(select.getText()).getRecord()
						        .setDates(doublylist.getNode(select.getText()).getRecord().getDates(), d);
						((DateStack) (doublylist.getNode(select.getText()).getRecord().getDates().findNode(d)
						        .getData())).setStack(m);
						((DateStack) (doublylist.getNode(select.getText()).getRecord().getDates().findNode(d)
						        .getData())).getStack().printStack();
						System.out.println(doublylist.getNode(select.getText()).getRecord().getDates().toString());
					} else {
						msg.setText("City not found!");
					}
				} else {
					msg.setText("All fields must be filled!");
				}
				load.fire();
				System.out.println(doublylist.getNode(select.getText()).getRecord().getNames().toString());
				System.out.println(doublylist.getNode(select.getText()).getRecord().getDates().toString());
			});

			TextField updtf1 = new TextField();
			TextField updtf2 = new TextField();
			TextField updtf3 = new TextField();
			TextField updtf4 = new TextField();
			Button updmar = new Button("Update");
			updmar.setOnAction(a -> {
				Martyr martyr = table.getSelectionModel().getSelectedItem();
				updtf1.setText(martyr.getName());
				updtf2.setText(martyr.getAge() + "");
				String dateod = martyr.getDateOfDeath().getMonth() + 1 + "/" + martyr.getDateOfDeath().getDate() + "/"
				        + (martyr.getDateOfDeath().getYear() + 1900);
				updtf3.setText(dateod);
				updtf4.setText(martyr.getGender() + "");
			});

			Label updlbl = new Label("Update the record: ");
			HBox marhb = new HBox(5);
			marhb.getChildren().addAll(select, load, msg);
			Label selectlbl = new Label("Select a martyr to delete or update: ");
			Button dlt = new Button("Delete");
			dlt.setOnAction(a -> {
				Martyr martyr = table.getSelectionModel().getSelectedItem();
				doublylist.getNode(select.getText()).getRecord().getNames().delete(martyr);
				System.out.println(doublylist.getNode(select.getText()).getRecord().getNames().getRoot());
				DateStack ds = new DateStack(table.getSelectionModel().getSelectedItem().getDateOfDeath());
				DateStack d = ((DateStack) (doublylist.getNode(select.getText()).getRecord().getDates().findNode(ds)
				        .getData()));
				Stack stack = d.getStack();
				Stack temp = new Stack();
				while (stack.peek() != table.getSelectionModel().getSelectedItem()) {
					temp.push(stack.pop());
				}
				stack.pop();
				while (!temp.isEmpty()) {
					stack.push(temp.pop());
				}
				load.fire();
				System.out.println(doublylist.getNode(select.getText()).getRecord().getNames().toString());
				System.out.println(doublylist.getNode(select.getText()).getRecord().getDates().toString());
			});
			HBox dltupd = new HBox(5);
			dltupd.getChildren().addAll(selectlbl, dlt, updmar);
			HBox updhb = new HBox(5);
			Button confirmupd = new Button("Update");
			confirmupd.setOnAction(a -> {
				dlt.fire();
				Date marDateOfDeath = new Date(updtf3.getText());
				Martyr m = new Martyr(updtf1.getText(), Integer.parseInt(updtf2.getText()), marDateOfDeath,
				        updtf4.getText().charAt(0));
				DateStack d = new DateStack(marDateOfDeath);
				doublylist.getNode(select.getText()).getRecord()
				        .setNames(doublylist.getNode(select.getText()).getRecord().getNames(), m);
				doublylist.getNode(select.getText()).getRecord()
				        .setDates(doublylist.getNode(select.getText()).getRecord().getDates(), d);
				((DateStack) (doublylist.getNode(select.getText()).getRecord().getDates().findNode(d).getData()))
				        .setStack(m);
				System.out.println(doublylist.getNode(select.getText()).getRecord().getNames().toString());
				System.out.println(doublylist.getNode(select.getText()).getRecord().getDates().toString());
				load.fire();
			});
			updhb.getChildren().addAll(name2, updtf1, age2, updtf2, date2, updtf3, gender2, updtf4, confirmupd);
			HBox addhb = new HBox(5);
			addhb.getChildren().addAll(name, intf1, age, intf2, date, intf3, gender, intf4, insertmar);

			HBox hbsearch = new HBox(5);
			Label srch = new Label("Search within this city");
			Button search = new Button("Search");
			hbsearch.getChildren().addAll(srch, search);
			VBox all = new VBox(5);

			search.setOnAction(a -> {
				Stage searchStage = new Stage();
				searchtxt = new TextField();
				Button searchbutton = new Button("search");
				HBox hbb2 = new HBox(5);
				hbb2.getChildren().addAll(searchtxt, searchbutton);
				hbb2.setAlignment(Pos.CENTER);
				TableView<Martyr> searchtable = new TableView<>();
				ObservableList<Martyr> searchdata = FXCollections.observableArrayList();
				searchtable.setItems(searchdata);
				TableColumn searchCol1 = new TableColumn("Name");
				searchCol1.setMinWidth(70);
				searchCol1.setCellValueFactory(new PropertyValueFactory<Martyr, String>("name"));
				TableColumn searchCol2 = new TableColumn("Age");
				searchCol2.setMinWidth(70);
				searchCol2.setCellValueFactory(new PropertyValueFactory<Martyr, Integer>("age"));
				TableColumn searchCol3 = new TableColumn("Date of death");
				searchCol3.setMinWidth(150);
				searchCol3.setCellValueFactory(new PropertyValueFactory<Martyr, Date>("dateOfDeath"));
				TableColumn searchCol4 = new TableColumn("Gender");
				searchCol4.setMinWidth(40);
				searchCol4.setCellValueFactory(new PropertyValueFactory<Martyr, Character>("gender"));
				searchtable.getColumns().addAll(searchCol1, searchCol2, searchCol3, searchCol4);
				searchtable.setMaxHeight(350);
				searchtable.setMaxWidth(370);
				BorderPane p = new BorderPane();
				p.setTop(hbb2);
				p.setCenter(searchtable);
				p.setPadding(new Insets(10, 10, 10, 10));
				searchbutton.setOnAction(z -> {

					AVLTree list = doublylist.getNode(select.getText()).getRecord().getNames();
					searchtable.getItems().clear();
					addDataToSearchTable(doublylist.getNode(select.getText()).getRecord().getNames().getRoot(),
					        searchdata);

				});
				Scene s = new Scene(p, 500, 500);
				searchStage.setScene(s);
				searchStage.show();
			});
			all.getChildren().addAll(hbsearch, dltupd, updhb, addhb);
			VBox martyrsvb = new VBox(10);
			martyrsvb.setPadding(new Insets(10, 10, 10, 10));
			martyrsvb.getChildren().addAll(marhb, table, all);
			martyrsvb.setAlignment(Pos.CENTER);
			Scene scene = new Scene(martyrsvb, 1100, 550);
			martyrsStage.setScene(scene);
			martyrsStage.show();
		});

		stat.setFocusTraversable(false);
		stat.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		stat.setPrefWidth(240);
		stat.setFont(font);
		stat.setOnMouseEntered(e ->

		{
			stat.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		stat.setOnMouseExited(e -> {
			stat.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		stat.setDisable(true);
		stat.setOnAction(l -> {
			statStage = new Stage();
			BorderPane pane = new BorderPane();
			Label select = new Label("Select Location: ");
			selecttxt = new TextField();
			showStatistics = new Button("Show info");
			selecttxt.setText(tf3.getText());
			Label a = new Label("*Nubmer of martyrs: ");
			Label b = new Label("*Traverse the martyr's info level by level: ");
			Label c = new Label("*The height of the 1st AVL tree: ");
			Label d = new Label("*Traverse the 2nd AVL tree backward: ");
			Label e = new Label("*The date that has the max number of martyrs: ");
			Label f = new Label("*The height of 2nd AVLtree: ");
			TextArea ans1 = new TextArea();
			ans1.setPrefHeight(20);
			TextArea ans2 = new TextArea();
			ans2.setPrefHeight(100);
			TextArea ans3 = new TextArea();
			ans3.setPrefHeight(20);
			TextArea ans4 = new TextArea();
			ans4.setPrefHeight(100);
			TextArea ans5 = new TextArea();
			ans5.setPrefHeight(20);
			TextArea ans6 = new TextArea();
			ans6.setPrefHeight(20);
			showStatistics.setOnAction(z -> {
				AVLTree firstavl = doublylist.getNode(selecttxt.getText()).getRecord().getNames();
				AVLTree2 avl2 = doublylist.getNode(selecttxt.getText()).getRecord().getDates();

				int count = firstavl.getNodeCount(firstavl.getRoot());
				ans1.setText("" + count);

				ans2.setText("" + levelByLevel(firstavl));

				ans3.setText("" + firstavl.height(firstavl.getRoot()));

				ans4.setText("" + avl2.backward());
				Queue q = toQueue(avl2);
//				q.printQueue();
				DateStack ds = (DateStack)(q.deQueue());
				DateStack max = ds;
				DateStack curr = ds;
				int maxsize = max.getStack().size();
				int currsize= curr.getStack().size();
				while(!q.isEmpty()) {
					curr = (DateStack)(q.deQueue());
					currsize = curr.getStack().size();
					if(currsize>maxsize) {
						max=curr;
						maxsize = currsize;
					}
				}
				ans5.setText("" + max.getDate());

				ans6.setText("" + avl2.height(avl2.getRoot()));
			});
			showStatistics.fire();

			ans1.setEditable(false);
			ans2.setEditable(false);
			ans3.setEditable(false);
			ans4.setEditable(false);
			ans5.setEditable(false);
			ans6.setEditable(false);
			Button next = new Button(">");
			Button prev = new Button("<");
			HBox hb = new HBox(5);
			hb.getChildren().addAll(select, selecttxt, showStatistics);
			VBox vb = new VBox(5);
			vb.getChildren().addAll(a, ans1, b, ans2, c, ans3, d, ans4, e, ans5, f, ans6);
			vb.setPadding(new Insets(10, 10, 10, 10));
			pane.setPadding(new Insets(10, 10, 10, 10));
			pane.setTop(hb);
			pane.setCenter(vb);
			pane.setRight(next);
			pane.setLeft(prev);
			pane.setAlignment(next, Pos.CENTER_RIGHT);
			pane.setAlignment(prev, Pos.CENTER_LEFT);
			next.setOnAction(y -> {
				if (selecttxt.getText() != "") {
					selecttxt.setText(doublylist.getNode(selecttxt.getText()).getNext().getRecord().getLocation());
					showStatistics.fire();
				}

			});
			prev.setOnAction(y -> {
				if (selecttxt.getText() != "") {
					selecttxt.setText(doublylist.getNode(selecttxt.getText()).getPrev().getRecord().getLocation());
					showStatistics.fire();
				}
			});
			Scene s = new Scene(pane, 900, 700);
			statStage.setScene(s);
			statStage.show();
		});
		save.setFocusTraversable(false);
		save.setStyle(
		        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		save.setPrefWidth(240);
		save.setFont(font);
		save.setOnMouseEntered(e -> {
			save.setStyle("-fx-background-radius: 0px;-fx-background-color:darkgrey;-fx-border-color:transparent;");

		});
		save.setOnMouseExited(e -> {
			save.setStyle(
			        "-fx-background-radius: 0px;-fx-background-color:black;-fx-border-color:transparent;-fx-text-fill:white;");
		});
		save.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File f = fc.showOpenDialog(primaryStage);
			try (FileWriter output = new FileWriter(f)) {
				if (f.length() == 0) {
					output.append("Name,Age,Event location - District,Date of death,Gender\r\n");
				}
				for (int i = 0; i < doublylist.count; i++) {
					AVLTree tree = doublylist.getNode(i).getRecord().getNames();
					Queue q = toQueue1(tree);
					int size = q.size();
					for (int j = 0; j <size; j++) {
						Martyr m = (Martyr)q.deQueue();
						String dateod = m.getDateOfDeath().getMonth() + 1 + "/" + m.getDateOfDeath().getDate() + "/"
						        + (m.getDateOfDeath().getYear() + 1900);
						try {
							output.append(m.getName() + "," + m.getAge() + "," + doublylist.getNode(i).getRecord().getLocation()
							        + "," + dateod + "," + m.getGender() + "\r\n");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			} catch (IOException ex) {
				System.out.println("Error");
				ex.printStackTrace();
			}
		});

		VBox vb = new VBox(1);
		vb.getChildren().addAll(btfile, location, martyrs, stat, save);
		HBox hb = new HBox();
		hb.getChildren().addAll(vb, iv);
		bp.setTop(logo);
		bp.setCenter(hb);

		Scene s = new Scene(bp, 1100, 650);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	private void addDataToTable(Node node, ObservableList<Martyr> data) {
		if (node != null) {
			Martyr martyr = (Martyr) node.getData();
			data.add(martyr);

			addDataToTable(node.getLeft(), data);
			addDataToTable(node.getRight(), data);
		}
	}

	private void addDataToSearchTable(Node node, ObservableList<Martyr> data) {
		if (node != null) {
			Martyr martyr = (Martyr) node.getData();
			if (martyr.getName().toLowerCase().contains(searchtxt.getText().toLowerCase())) {
				data.add(martyr);
			}

			addDataToSearchTable(node.getLeft(), data);
			addDataToSearchTable(node.getRight(), data);
		}
	}

	public static String levelByLevel(AVLTree tree) {
		String ans = "";
		if (tree.getRoot() == null) {
			System.out.println("Tree is empty");
			return null;
		}
		Queue queue = new Queue();
		queue.enQueue(tree.getRoot());

		while (!queue.isEmpty()) {
			Node node = (Node) (queue.deQueue());
			ans += (node.getData().toString() + " \n");

			if (node.getLeft() != null)
				queue.enQueue(node.getLeft());

			if (node.getRight() != null)
				queue.enQueue(node.getRight());
		}
		return ans;
	}

	public static Queue toQueue(AVLTree2 tree) {
		Queue queue = new Queue();
		if (tree.getRoot() == null) {
			System.out.println("Tree is empty");
			return queue;
		}

		Queue temp = new Queue();
		queue.enQueue(tree.getRoot());

		while (!queue.isEmpty()) {
			Node2 node = (Node2)(queue.deQueue());
			temp.enQueue(node.getData());
			if (node.getLeft() != null)
				queue.enQueue(node.getLeft());

			if (node.getRight() != null)
				queue.enQueue(node.getRight());

		}
		return temp;
	}
	public static Queue toQueue1(AVLTree tree) {
		Queue queue = new Queue();
		if (tree.getRoot() == null) {
			System.out.println("Tree is empty");
			return queue;
		}

		Queue temp = new Queue();
		queue.enQueue(tree.getRoot());

		while (!queue.isEmpty()) {
			Node node = (Node)(queue.deQueue());
			temp.enQueue(node.getData());
			if (node.getLeft() != null)
				queue.enQueue(node.getLeft());

			if (node.getRight() != null)
				queue.enQueue(node.getRight());

		}
		return temp;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
