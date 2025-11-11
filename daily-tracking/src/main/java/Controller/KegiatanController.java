package Controller;

import Model.Kegiatan;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class KegiatanController implements Initializable {

    @FXML
    private TextField txtNama;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private CheckBox cbSelesai;
    @FXML
    private TableView<Kegiatan> tvKegiatan;
    @FXML
    private TableColumn<Kegiatan, String> colNama;
    @FXML
    private TableColumn<Kegiatan, LocalDate> colTanggal;
    @FXML
    private TableColumn<Kegiatan, Boolean> colSelesai;

    private ObservableList<Kegiatan> dataKegiatan;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataKegiatan = FXCollections.observableArrayList(
                new Kegiatan("Belajar JavaFX", LocalDate.now(), false),
                new Kegiatan("Olahraga", LocalDate.now().minusDays(1), true)
        );

        colNama.setCellValueFactory(new PropertyValueFactory<>("namaKegiatan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colSelesai.setCellValueFactory(new PropertyValueFactory<>("selesai"));

        tvKegiatan.setItems(dataKegiatan);

        tvKegiatan.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> showDetails(newSel)
        );
    }

    private void showDetails(Kegiatan keg) {
        if (keg != null) {
            txtNama.setText(keg.getNamaKegiatan());
            dpTanggal.setValue(keg.getTanggal());
            cbSelesai.setSelected(keg.isSelesai());
        } else {
            clearFields();
        }
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        if (txtNama.getText().isEmpty() || dpTanggal.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Isi semua field terlebih dahulu.");
            return;
        }

        dataKegiatan.add(new Kegiatan(txtNama.getText(), dpTanggal.getValue(), cbSelesai.isSelected()));
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Kegiatan berhasil ditambahkan.");
    }

    @FXML
    private void handleEdit(ActionEvent event) {
        Kegiatan selected = tvKegiatan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNamaKegiatan(txtNama.getText());
            selected.setTanggal(dpTanggal.getValue());
            selected.setSelesai(cbSelesai.isSelected());
            tvKegiatan.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Kegiatan berhasil diubah.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih kegiatan yang ingin diubah.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Kegiatan selected = tvKegiatan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dataKegiatan.remove(selected);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Kegiatan berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih kegiatan yang ingin dihapus.");
        }
    }

    private void clearFields() {
        txtNama.clear();
        dpTanggal.setValue(null);
        cbSelesai.setSelected(false);
        tvKegiatan.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
