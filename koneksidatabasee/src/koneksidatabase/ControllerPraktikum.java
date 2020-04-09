package koneksidatabase;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ControllerPraktikum {
    //perantara model dan view
    ModelPraktikum modelPraktikum;
    ViewPraktikum viewPraktikum;

    public ControllerPraktikum(ModelPraktikum modelPraktikum, ViewPraktikum viewPraktikum) {
        this.modelPraktikum = modelPraktikum;
        this.viewPraktikum = viewPraktikum;

        if (modelPraktikum.getBanyakData() != 0) { //kalau banyak datanya tidak sama dengan 0
            String dataMahasiswa[][] = modelPraktikum.readMahasiswa(); //ambil method readMahasiswa di model
            viewPraktikum.tabel.setModel((new JTable(dataMahasiswa, viewPraktikum.namaKolom)).getModel());
           //menampilkan data yang ada didalam database ke tabel
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        viewPraktikum.btnTambahPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewPraktikum.getNim().equals("")
                        || viewPraktikum.getNama().equals("")
                        || viewPraktikum.getAlamat().equals("")) {
                    JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
                } else {
                    String nim = viewPraktikum.getNim();
                    String nama = viewPraktikum.getNama();
                    String alamat = viewPraktikum.getAlamat();
                    modelPraktikum.insertMahasiswa(nim, nama, alamat);
                    viewPraktikum.tfNim.setText("");
                    viewPraktikum.tfNamaMhs.setText("");
                    viewPraktikum.tfAlamatMhs.setText("");
                    //untuk menampilkan output langsung tanpa reload
                    String dataMahasiswa[][] = modelPraktikum.readMahasiswa();
                    viewPraktikum.tabel.setModel(new JTable(dataMahasiswa, viewPraktikum.namaKolom).getModel());
                }
            }
        });

        viewPraktikum.btnUpdatePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewPraktikum.getNim().equals("")
                        || viewPraktikum.getNama().equals("")
                        || viewPraktikum.getAlamat().equals("")) {
                    JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
                } else {
                    String nim = viewPraktikum.getNim();
                    String nama = viewPraktikum.getNama();
                    String alamat = viewPraktikum.getAlamat();
                    modelPraktikum.updateMahasiswa(nim, nama, alamat);
                    viewPraktikum.tfNim.setText("");
                    viewPraktikum.tfNamaMhs.setText("");
                    viewPraktikum.tfAlamatMhs.setText("");
                    String dataMahasiswa[][] = modelPraktikum.readMahasiswa();
                    viewPraktikum.tabel.setModel(new JTable(dataMahasiswa, viewPraktikum.namaKolom).getModel());
                }
            }
        });

        viewPraktikum.btnHapusPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPraktikum.tfNimdel.getText();
                 int input = JOptionPane.showConfirmDialog(null,
                        "Apa anda ingin menghapus NIM "+  viewPraktikum.tfNimdel.getText()+ "?", "Pilih Opsi...",JOptionPane.YES_NO_CANCEL_OPTION);

                if(input==0){
                    modelPraktikum.deleteMahasiswa( viewPraktikum.tfNimdel.getText()); //mengambil method hapus di model
                    String dataMahasiswa[][] = modelPraktikum.readMahasiswa();
                    viewPraktikum.tabel.setModel(new JTable(dataMahasiswa, viewPraktikum.namaKolom).getModel());
                }else{
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
    }
}
