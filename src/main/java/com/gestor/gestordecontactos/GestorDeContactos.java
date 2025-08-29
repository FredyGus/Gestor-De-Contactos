package com.gestor.gestordecontactos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase Contacto
 * Representa un contacto con nombre y teléfono.
 */
class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Métodos getter y setterD
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }
}

/**
 * Clase GestorDeContactos
 * Ventana principal que permite gestionar contactos:
 * Agregar, eliminar, modificar y guardar en un archivo.
 */
public class GestorDeContactos extends JFrame {
    // Componentes de la interfaz
    private JTextField txtNombre, txtTelefono;
    private JButton btnAgregar, btnEliminar, btnModificar;
    private JTable tabla;
    private DefaultTableModel modelo;
    
    // Lista en memoria para manejar contactos
    private ArrayList<Contacto> listaContactos;

    // Archivo donde se guardan los contactos
    private final String ARCHIVO = "contactos.txt";

    /**
     * Constructor de la ventana principal
     */
    public GestorDeContactos() {
        setTitle("Gestor de Contactos");  // Título de la ventana
        setSize(600, 400);                // Tamaño inicial
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);      // Centrar ventana

        listaContactos = new ArrayList<>();

        // ---------------- Panel de entrada ----------------
        // Permite ingresar nombre y teléfono
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        panelEntrada.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);
        panelEntrada.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panelEntrada.add(txtTelefono);

        // ---------------- Panel de botones ----------------
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);

        // ---------------- Tabla de contactos ----------------
        modelo = new DefaultTableModel(new String[]{"Nombre", "Telefono"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // ---------------- Layout principal ----------------
        setLayout(new BorderLayout(10, 10));
        add(panelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // ---------------- Eventos ----------------
        btnAgregar.addActionListener(e -> agregarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnModificar.addActionListener(e -> modificarContacto());

        // Cargar contactos al iniciar
        cargarContactos();

        // Guardar contactos al cerrar ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarContactos();
            }
        });
    }

    /**
     * Agrega un nuevo contacto a la lista y la tabla
     */
    private void agregarContacto() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (!nombre.isEmpty() && !telefono.isEmpty()) {
            Contacto c = new Contacto(nombre, telefono);
            listaContactos.add(c);
            modelo.addRow(new Object[]{c.getNombre(), c.getTelefono()});
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese nombre y telefono.");
        }
    }

    /**
     * Elimina el contacto seleccionado de la lista y la tabla
     */
    private void eliminarContacto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            listaContactos.remove(fila);
            modelo.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    /**
     * Modifica el contacto seleccionado con los nuevos datos
     */
    private void modificarContacto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            String nuevoNombre = txtNombre.getText().trim();
            String nuevoTelefono = txtTelefono.getText().trim();

            if (!nuevoNombre.isEmpty() && !nuevoTelefono.isEmpty()) {
                Contacto c = listaContactos.get(fila);
                c.setNombre(nuevoNombre);
                c.setTelefono(nuevoTelefono);

                // Actualizar tabla
                modelo.setValueAt(nuevoNombre, fila, 0);
                modelo.setValueAt(nuevoTelefono, fila, 1);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese nombre y telefono.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para modificar.");
        }
    }

    /**
     * Limpia los campos de entrada
     */
    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
    }

    /**
     * Carga los contactos desde el archivo en la lista y tabla
     */
    private void cargarContactos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    Contacto c = new Contacto(datos[0], datos[1]);
                    listaContactos.add(c);
                    modelo.addRow(new Object[]{c.getNombre(), c.getTelefono()});
                }
            }
        } catch (IOException e) {
            System.out.println("No se encontro archivo, se creara al guardar.");
        }
    }

    /**
     * Guarda los contactos en el archivo de texto
     */
    private void guardarContactos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Contacto c : listaContactos) {
                bw.write(c.getNombre() + "," + c.getTelefono());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorDeContactos().setVisible(true));
    }
}
