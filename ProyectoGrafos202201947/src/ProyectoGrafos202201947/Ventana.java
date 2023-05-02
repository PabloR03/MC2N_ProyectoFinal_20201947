package ProyectoGrafos202201947;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


/**
 *
 * @author Pablo Rodriguez
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    
    ArrayList<Vertice> nodos;
    ArrayList<Arista> arcos;
    int cantNodos = 0;
    Vertice nodoinicial = null;
    Vertice nodofinal = null;
    int tamNodos = 30;
    int[][] matriz;
    int a=0;
    
    public Ventana() {
        initComponents();
        nodos = new ArrayList<Vertice>();
        arcos = new ArrayList<Arista>();
        Insertar(jPanel1.getGraphics());
    }
    
    private void Insertar(Graphics g){
        jPanel1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jRadioButton1.isSelected()){
                    g.setColor(Color.BLUE);
                    g.fillOval(e.getX() - (tamNodos/2), e.getY() - (tamNodos/2), tamNodos, tamNodos);
                    nodos.add(new Vertice(cantNodos, e.getX()- (tamNodos/2), e.getY()- (tamNodos/2), Color.WHITE));
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(cantNodos), e.getX(), e.getY());
                    cantNodos++;
                }else{
                    if(nodoinicial == null){
                        nodoinicial = BuscarNodo(e.getX(), e.getY());
                        if(nodoinicial != null){
                            seleccionarNodo(nodoinicial, g, Color.red);
                        }
                    }else{
                        nodofinal = BuscarNodo(e.getX(), e.getY());
                        if(nodofinal!=null){
                            seleccionarNodo(nodofinal, g, Color.red);
                            if(nodofinal.name != nodoinicial.name){
                                g.setColor(Color.black);
                                g.drawLine(nodoinicial.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodofinal.posy + (tamNodos/2));
                                int distancia = distancia(nodoinicial.posx + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), nodofinal.posy + (tamNodos/2));
                                arcos.add(new Arista(nodoinicial.name, nodofinal.name, 
                                        nodoinicial.posx + (tamNodos/2), nodoinicial.posy + (tamNodos/2), 
                                        nodofinal.posx + (tamNodos/2), nodofinal.posy + (tamNodos/2), distancia));
                                
                                jComboBox1.addItem( a +". Distancia: " + distancia );
                                a++;
                                //System.out.println("distancia: " + distancia);
                            }else{
                                seleccionarNodo(nodoinicial, g, Color.black);
                            }
                            seleccionarNodo(nodoinicial, g, Color.black);
                            seleccionarNodo(nodofinal, g, Color.black);
                            nodoinicial = null;
                        }else{
                            seleccionarNodo(nodoinicial, g, Color.black);
                            nodoinicial = null;
                        }
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
 });
    }
    
    private int distancia(int x1, int x2, int y1, int y2){
        double dist = Math.sqrt(Math.pow(x2-x1, 2.0) + Math.pow(y2-y1, 2.0));
        return (int)dist;
    }
    
    private void seleccionarNodo(Vertice nodo, Graphics g, Color color){
        g.setColor(color);
        g.drawOval(nodo.posx, nodo.posy, tamNodos-1, tamNodos-1);
    }
    
    private Vertice BuscarNodo(int coordx, int coordy){
        Vertice nodoReturn = null;
        for (Vertice nodo : nodos) {
            if(coordx >= nodo.posx && coordx <= nodo.posx + tamNodos && 
                    coordy >= nodo.posy && coordy <= nodo.posy + tamNodos){
                nodoReturn = nodo;
                break;
            }
        }
        return nodoReturn;
    }
    
    private void calcularMatriz(){
        matriz = new int[nodos.size()][nodos.size()];
        for (Arista arco : arcos) {
            matriz[arco.nodoinicial][arco.nodofinal] = arco.dist;
            matriz[arco.nodofinal][arco.nodoinicial] = arco.dist;
        }
    }
    
    private void Prim(int[][] matriz){
        boolean vector[]  =new boolean[nodos.size()];
        vector[0] = true;
        while(faltaAlguno(vector)){
            int min = menor(matriz, vector);
            vector[min] = true;
        }
    }
    
    private int menor(int[][] matriz, boolean[] vector){
        int menor = Integer.MAX_VALUE;
        int fila = -1, col=-1;
        for (int i = 0; i < matriz.length; i++) {
            if(vector[i]){
                for (int j = 0; j < matriz.length; j++) {
                    if(matriz[j][i]!=0 && vector[j]==false && matriz[j][i]<=menor){
                        menor = matriz[j][i];
                        fila = j;
                        col = i;
                    }
                }
            }
        }
        
        System.out.println("" + fila + " - " + col);
        return fila;
    }
    
    private boolean faltaAlguno(boolean[] vector){
        for (int i = 0; i < vector.length; i++) {
            if(!vector[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setText("Vértice");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setText("Arista");

        jButton1.setText("Ver Conecciones de aristas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Listado de Distancia de Aristas:" }));

        jLabel1.setText("PROYECTO GRAFOS MC2N 202201947");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        calcularMatriz();
        Prim(matriz);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
