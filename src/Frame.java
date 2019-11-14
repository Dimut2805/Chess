import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class Frame extends JFrame implements MouseListener {
    static boolean it_hod_is_blue = true; //Переменная для определения. Чей ход?
    private final static int WIDTH = 1000,    //Ширина и высота доски
            HEIGHT = 700;
    final static int WIDTH_HEIGHT_RECT = 70, //Длина и ширина квадратика
            WIDTH_HEIGHT_DESK = 560; //Длинна и ширина доски
    final static int BEGIN_X = 100, BEGIN_Y = 70; //Начало доски по координатам
    static boolean bool_coor; //Проверка, мышка ли в доске?
    static int[] spis_coor_for_obvod = new int[2];
    private static boolean bool_enter_or_no_obvod = false; //Есть ли обводка или нет
    private static boolean bool_figure_is_not_vrag; //Своя фигура?
    private static int coor_first_click_y, coor_first_click_x, number_figure_click;
    private Pole pole = new Pole(); //Класс работы с полем
    private static Figure figure = new Figure();//Класс реализации фигур
    private static Frame frame; //Сам главный барин
    private static Panel panel = new Panel(); //Холст, я тута рисую
    private static FigureRed figureRed = new FigureRed();
    private static Image image_horse_black = new ImageIcon("src/horse.png").getImage(); //Конь черных
    private static Image image_horse_white = new ImageIcon("src/horse_white.png").getImage(); //Конь белых
    private static Image image_korol_black = new ImageIcon("src/korol.png").getImage(); //Король черных
    private static Image image_korol_white = new ImageIcon("src/korol_white.png").getImage(); //Король белых
    private static Image image_koroleva_black = new ImageIcon("src/koroleva.png").getImage(); //Королева черных
    private static Image image_koroleva_white = new ImageIcon("src/koroleva_white.png").getImage();//Королева белых
    private static Image image_oficer_black = new ImageIcon("src/oficer.png").getImage(); //Офицер черных
    private static Image image_oficer_white = new ImageIcon("src/oficer_white.png").getImage();//Офицер белых
    private static Image image_peshka_black = new ImageIcon("src/peshka.png").getImage(); //Пешка черных
    private static Image image_peshka_white = new ImageIcon("src/peshka_white.png").getImage();//Пешка белых
    private static Image image_slone_black = new ImageIcon("src/slone.png").getImage(); //Слон черных
    private static Image image_slone_white = new ImageIcon("src/slone_white.png").getImage();//Слон белых

    private static Image image_orange_bum = new ImageIcon("src/orange_bum.png").getImage();


    private static Image image_background_gameover = new ImageIcon("src/backgroundGameOver.png").getImage(); //Фон

    private static Image image_button_exit = new ImageIcon("src/buttonExit.png").getImage();
    private static Image image_button_new_game = new ImageIcon("src/buttonNewGame.png").getImage();


    private static Image image_yellow_shag = new ImageIcon("src/yellow_shag.png").getImage();


    static String namePlayer = "Blue";

    static int[][] spis_button = {
            {300, 250, 120, 120}, //new game for GameOver 0
            {500, 250, 120, 120}, //Exit for GameOver 1
            {300, 250, 120, 120}, //Start menu 2
            {500, 250, 120, 120},//Exit menu 3
            {750, 500, 120, 120},//Exit game 4
            {750, 300, 120, 120}//new game in game 5
    };


    static int[][] spis_shah_korol = { //Карта для поиска шахов
            {0, 0, 0, 0, 0, 0, 0, 0}, //Доработать
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};


    static int[][] spis_game_pole_var = { //карта боя
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    /*  0-Пусто
        1-Пешка
        2-Слон
        3-Конь
        4-Офицер
        5-Королева
        6-Царь батюшка
     */
    //Синие - черный, красные - белые
    // 100 - желтый ход, 101 - оранжевый удар
    static int[][] spis_game_pole = {
            {2, 3, 4, 6, 5, 4, 3, 2},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {11, 11, 11, 11, 11, 11, 11, 11},
            {22, 33, 44, 55, 66, 44, 33, 22}};
    static Image[] SPIS_GAME_CHIS_EVERY_FIGURE_IMAGE = { //Мы с Томарой
            image_horse_black,
            image_korol_black,
            image_koroleva_black,
            image_oficer_black,
            image_peshka_black,
            image_slone_black,
            image_horse_white,
            image_korol_white,
            image_koroleva_white,
            image_oficer_white,
            image_peshka_white,
            image_slone_white,
    };
    static int[] SPIS_GAME_CHIS_EVERY_FIGURE_CHIS = {33, 66, 55, 44, 11, 22, 3, 6, 5, 4, 1, 2}; //Ходим парой

    Frame() {
        addMouseListener(this); //Активация мышки
        setTitle("Ах, это же шахматы!");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        add(panel);
        setVisible(true);
    }

    public static void drawPictureGameOver(Graphics g) {
        g.drawImage(image_background_gameover, 0, 0, 1000, 700, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        if (Panel.victory.equals("blue")) {
            g.drawString("Победил - Синий!", 250, 120);
        } else {
            g.drawString("Победил - Красный", 250, 120);
        }
        g.drawImage(image_button_new_game, spis_button[0][0], spis_button[0][1], spis_button[0][2], spis_button[0][3], null);
        g.drawImage(image_button_exit, spis_button[1][0], spis_button[1][1], spis_button[1][2], spis_button[1][3], null);


    }

    public static void drawPictureMenu(Graphics g) {
        g.drawImage(image_background_gameover, 0, 0, 1000, 700, null);
        g.drawImage(image_button_new_game, spis_button[2][0], spis_button[2][1], spis_button[2][2], spis_button[2][3], null);
        g.drawImage(image_button_exit, spis_button[3][0], spis_button[3][1], spis_button[3][2], spis_button[3][3], null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString("Добро пожаловать в игру!", 160, 120);
    }

    public static void drawPicture(Graphics g) { //Рисование поля
        boolean desk_bool = false;
        int have_you_korol_blue = 0, have_you_korol_red = 0; //Проверка на наличие королей
        g.drawImage(image_background_gameover, 0, 0, 1000, 700, null);
        g.drawImage(image_button_new_game, spis_button[5][0], spis_button[5][1], spis_button[5][2], spis_button[5][3], null);//Кнопка новой игры
        g.drawImage(image_button_exit, spis_button[4][0], spis_button[4][1], spis_button[4][2], spis_button[4][3], null);//Кнопка выхода
        g.setColor(Color.BLACK);
        g.fillRect(BEGIN_X - 30, BEGIN_Y - 30, 620, 620);
        for (int i = 0, y = 0; i < 8; i++, y += WIDTH_HEIGHT_RECT) {
            for (int j = 0, x = 0; j < 8; j++, x += WIDTH_HEIGHT_RECT) {
                if (spis_game_pole[i][j] == 66) have_you_korol_blue = 1;
                if (spis_game_pole[i][j] == 6) have_you_korol_red = 1;
                if (!bool_enter_or_no_obvod && spis_game_pole[i][j] > 99) {
                    spis_game_pole[i][j] = 0;
                }//Возможные ходы и удары убираются
                if (!bool_enter_or_no_obvod) {
                    spis_game_pole_var[i][j] = 0;
                }
                if (spis_shah_korol[i][j] == 1) {
                    g.drawImage(image_orange_bum, x + BEGIN_X, y + BEGIN_Y, WIDTH_HEIGHT_RECT, WIDTH_HEIGHT_RECT, null);
                } else if (spis_game_pole[i][j] < 100) {
                    if (j % 2 == 0 && desk_bool == true) g.setColor(Color.WHITE);
                    else if (j % 2 != 0 && desk_bool == true) g.setColor(Color.DARK_GRAY);
                    else if (j % 2 == 0 && desk_bool == false) g.setColor(Color.DARK_GRAY);
                    else if (j % 2 != 0 && desk_bool == false) g.setColor(Color.WHITE);
                }
                if (spis_game_pole[i][j] == 100) {
                    g.drawImage(image_yellow_shag, x + BEGIN_X, y + BEGIN_Y, WIDTH_HEIGHT_RECT, WIDTH_HEIGHT_RECT, null);
                } else if (spis_game_pole_var[i][j] == 1) {
                    g.drawImage(image_orange_bum, x + BEGIN_X, y + BEGIN_Y, WIDTH_HEIGHT_RECT, WIDTH_HEIGHT_RECT, null);
                } else {
                    g.fillRect(x + BEGIN_X, y + BEGIN_Y, WIDTH_HEIGHT_RECT, WIDTH_HEIGHT_RECT);
                }
                if (Frame.bool_enter_or_no_obvod && Frame.bool_figure_is_not_vrag && Frame.spis_coor_for_obvod[1] == i && Frame.spis_coor_for_obvod[0] == j) { //Делает обводку клеточке
                    g.setColor(Color.GREEN);
                    g.fillRect(x + BEGIN_X, y + BEGIN_Y, WIDTH_HEIGHT_RECT, WIDTH_HEIGHT_RECT);
                    if (spis_game_pole[i][j] == 11) {
                        figure.peshka();
                    } else if (spis_game_pole[i][j] == 22) {
                        figure.slone();
                    } else if (spis_game_pole[i][j] == 44) {
                        figure.oficer();
                    } else if (spis_game_pole[i][j] == 55) {
                        figure.slone();
                        figure.oficer();
                    } else if (spis_game_pole[i][j] == 66) {
                        figure.korol();
                    } else if (spis_game_pole[i][j] == 33) {
                        figure.horse();
                    } else if (spis_game_pole[i][j] == 1) {
                        figureRed.peshka();
                    } else if (spis_game_pole[i][j] == 2) {
                        figureRed.slone();
                    } else if (spis_game_pole[i][j] == 4) {
                        figureRed.oficer();
                    } else if (spis_game_pole[i][j] == 5) {
                        figureRed.slone();
                        figureRed.oficer();
                    } else if (spis_game_pole[i][j] == 6) {
                        figureRed.korol();
                    } else if (spis_game_pole[i][j] == 3) {
                        figureRed.horse();
                    }
                }

                //Пешка подошла к концу карты и стала ферзем
                if (i == 0 || i == 7) {
                    if (spis_game_pole[i][j] == 1) {
                        spis_game_pole[i][j] = 5;
                    } else if (spis_game_pole[i][j] == 11) {
                        spis_game_pole[i][j] = 55;
                    }
                }
                //---


                for (int k = 0; k < SPIS_GAME_CHIS_EVERY_FIGURE_CHIS.length; k++) {//Отрисовка фигур
                    if (spis_game_pole[i][j] == SPIS_GAME_CHIS_EVERY_FIGURE_CHIS[k]) {
                        g.drawImage(SPIS_GAME_CHIS_EVERY_FIGURE_IMAGE[k], x + BEGIN_X, y + BEGIN_Y, 70, 70, null);
                        break;
                    }
                }
            }
            if (desk_bool == false) desk_bool = true;
            else desk_bool = false;
        }
        //Концовка
        if (have_you_korol_blue == 0 || have_you_korol_red == 0) {
            if (have_you_korol_blue == 0) {
                Panel.victory = "red";
            } else if (have_you_korol_red == 0) {
                Panel.victory = "blue";
            }
            Panel.menu_or_game_or_gameover = 3;
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.drawString(namePlayer, 750, 250);
    }


    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (Panel.menu_or_game_or_gameover == 1) {
            if (panel.button_start(e.getX(), e.getY())) {
                Panel.menu_or_game_or_gameover = 2;
            }
            if (panel.button_exit(e.getX(), e.getY(), 3)) {
                System.exit(0);
            }
        } else if (Panel.menu_or_game_or_gameover == 2) { //Игра началась
            if (panel.button_exit(e.getX(), e.getY(), 4)) {
                System.exit(0);
            }
            if (panel.button_begin_new_game(e.getX(), e.getY(), 5)) {
                Panel.menu_or_game_or_gameover = 2; //Переходим к игре
                namePlayer = "Blue";
                it_hod_is_blue = true; //Первый ходит синий

                //Начальная форма красных
                spis_game_pole[0][0] = 2;
                spis_game_pole[0][1] = 3;
                spis_game_pole[0][2] = 4;
                spis_game_pole[0][3] = 6;
                spis_game_pole[0][4] = 5;
                spis_game_pole[0][5] = 4;
                spis_game_pole[0][6] = 3;
                spis_game_pole[0][7] = 2;

                //Начальная форма синих
                spis_game_pole[7][0] = 22;
                spis_game_pole[7][1] = 33;
                spis_game_pole[7][2] = 44;
                spis_game_pole[7][3] = 55;
                spis_game_pole[7][4] = 66;
                spis_game_pole[7][5] = 44;
                spis_game_pole[7][6] = 33;
                spis_game_pole[7][7] = 22;

                for (int i = 1; i < 7; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (i == 1) {
                            spis_game_pole[i][j] = 1;
                        } else if (i == 6) {
                            spis_game_pole[i][j] = 11;
                        } else {
                            spis_game_pole[i][j] = 0;
                        }
                    }
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        spis_game_pole_var[i][j] = 0;
                    }
                }

            }


            if (it_hod_is_blue) { //Ход человека
                pole.getCor(e.getX(), e.getY());
                Frame.bool_figure_is_not_vrag = false;
                if (Frame.bool_coor) {//Проверка, мышка в поле или вне
                    //Шаг и убийство
                    if ((Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] == 100 || Frame.spis_game_pole_var[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] == 1) && Frame.bool_enter_or_no_obvod) {
                        Frame.spis_game_pole[coor_first_click_y][coor_first_click_x] = 0;
                        Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] = number_figure_click;
                        it_hod_is_blue = false; //Переход хода к red
                        namePlayer = "Red";
                    }
                    //---

                    if (Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] > 10) { //Проверка на свою фигуру
                        Frame.bool_figure_is_not_vrag = true;
                    }
                    if (Frame.bool_enter_or_no_obvod) { //Проверка для сменки bool
                        Frame.bool_enter_or_no_obvod = false;//-
                    } else if (Frame.bool_enter_or_no_obvod == false) {//-
                        coor_first_click_y = Frame.spis_coor_for_obvod[1];
                        coor_first_click_x = Frame.spis_coor_for_obvod[0];
                        number_figure_click = Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0]];
                        Frame.bool_enter_or_no_obvod = true;//-
                    }//.
                }
            } else { //Ход игрока 2
                pole.getCor(e.getX(), e.getY());
                Frame.bool_figure_is_not_vrag = false;
                if (Frame.bool_coor) {//Проверка, мышка в поле или вне
                    //Шаг и убийство
                    if ((Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] == 100 || Frame.spis_game_pole_var[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] == 1) && Frame.bool_enter_or_no_obvod) {
                        Frame.spis_game_pole[coor_first_click_y][coor_first_click_x] = 0;
                        Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] = number_figure_click;
                        it_hod_is_blue = true; //Переход хода к другому игроку
                        namePlayer = "Blue";
                    }
                    //---

                    if (Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] < 10 && Frame.spis_game_pole[pole.getCor(e.getX(), e.getY())[1]][pole.getCor(e.getX(), e.getY())[0]] != 0) { //Проверка на свою фигуру
                        Frame.bool_figure_is_not_vrag = true;
                    }
                    if (Frame.bool_enter_or_no_obvod) { //Проверка для сменки bool
                        Frame.bool_enter_or_no_obvod = false;//-
                    } else if (Frame.bool_enter_or_no_obvod == false) {//-
                        coor_first_click_y = Frame.spis_coor_for_obvod[1];
                        coor_first_click_x = Frame.spis_coor_for_obvod[0];
                        number_figure_click = Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0]];
                        Frame.bool_enter_or_no_obvod = true;//-
                    }//.
                }
            }
        } else { //new game
            if (panel.button_begin_new_game(e.getX(), e.getY(), 0)) {
                namePlayer = "Blue";
                Panel.menu_or_game_or_gameover = 2; //Переходим к игре
                it_hod_is_blue = true; //Первый ходит синий

                //Начальная форма красных
                spis_game_pole[0][0] = 2;
                spis_game_pole[0][1] = 3;
                spis_game_pole[0][2] = 4;
                spis_game_pole[0][3] = 6;
                spis_game_pole[0][4] = 5;
                spis_game_pole[0][5] = 4;
                spis_game_pole[0][6] = 3;
                spis_game_pole[0][7] = 2;

                //Начальная форма синих
                spis_game_pole[7][0] = 22;
                spis_game_pole[7][1] = 33;
                spis_game_pole[7][2] = 44;
                spis_game_pole[7][3] = 55;
                spis_game_pole[7][4] = 66;
                spis_game_pole[7][5] = 44;
                spis_game_pole[7][6] = 33;
                spis_game_pole[7][7] = 22;

                for (int i = 1; i < 7; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (i == 1) {
                            spis_game_pole[i][j] = 1;
                        } else if (i == 6) {
                            spis_game_pole[i][j] = 11;
                        } else {
                            spis_game_pole[i][j] = 0;
                        }
                    }
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        spis_game_pole_var[i][j] = 0;
                    }
                }

            }
            if (panel.button_exit(e.getX(), e.getY(), 1)) {
                System.exit(0);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        frame = new Frame();
    }

    private static class Panel extends JPanel implements ActionListener { //Класс для поля
        static String victory;
        Timer timer = new Timer(20, this);
        static int menu_or_game_or_gameover = 1;

        Panel() {
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (menu_or_game_or_gameover == 2) {
                drawPicture(g);
            } else if (menu_or_game_or_gameover == 3) {
                drawPictureGameOver(g);
            } else {
                drawPictureMenu(g);
            }
        }

        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        public boolean button_begin_new_game(int x, int y, int index) {
            if (x > spis_button[index][0] && x < spis_button[index][0] + spis_button[index][2] && y > spis_button[index][1] && y < spis_button[index][1] + spis_button[index][3]) {
                return true;
            } else {
                return false;
            }
        }

        public boolean button_exit(int x, int y, int index) {
            if (x > spis_button[index][0] && x < spis_button[index][0] + spis_button[index][2] && y > spis_button[index][1] && y < spis_button[index][1] + spis_button[index][3]) {
                return true;
            } else {
                return false;
            }
        }

        public boolean button_start(int x, int y) {
            if (x > spis_button[2][0] && x < spis_button[2][0] + spis_button[2][2] && y > spis_button[2][1] && y < spis_button[2][1] + spis_button[2][3]) {
                return true;
            } else {
                return false;
            }
        }
    }
}


class Pole {
    int[] getCor(double x, double y) {
        Frame.bool_coor = false;
        int[] spis = new int[2];
        if ((x < Frame.BEGIN_X || x > Frame.BEGIN_X + Frame.WIDTH_HEIGHT_DESK) || (y < Frame.BEGIN_Y || y > Frame.BEGIN_Y + Frame.WIDTH_HEIGHT_DESK)) {
            return spis;
        }
        break_one:
        for (int i = 0, y1 = Frame.BEGIN_Y + 30; i < 8; i++, y1 += Frame.WIDTH_HEIGHT_RECT) {
            for (int j = 0, x1 = Frame.BEGIN_X + 5; j < 8; j++, x1 += Frame.WIDTH_HEIGHT_RECT) {
                if (x > x1 && x < x1 + Frame.WIDTH_HEIGHT_RECT && y > y1 && y < y1 + Frame.WIDTH_HEIGHT_RECT) {
                    spis[0] = j;
                    spis[1] = i;
                    Frame.spis_coor_for_obvod[0] = j;
                    Frame.spis_coor_for_obvod[1] = i;
                    break break_one;
                }
            }
        }
        Frame.bool_coor = true;
        return spis;
    }
}

class Figure { //Класс под синего игрока
    public void peshka() {
        //Разработка ходьбы
        if (Frame.spis_coor_for_obvod[1] == 6) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 100;
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0]] != 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 100;
            }
        } else {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_coor_for_obvod[1] != 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 100;
            }
        }
        //Разработка убийства
        if (Frame.spis_coor_for_obvod[0] == 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        } else if (Frame.spis_coor_for_obvod[0] == 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        } else {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
    }

    public void slone() {
        for (int i = 1; true; i++) { //Шаги и убийство вправо
            if (Frame.spis_coor_for_obvod[0] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[0] + i == 7) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства влево
            if (Frame.spis_coor_for_obvod[0] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[0] - i == 0) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства вверх
            if (Frame.spis_coor_for_obvod[1] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства вниз
            if (Frame.spis_coor_for_obvod[1] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7) {
                break;
            }
        }
    }

    public void oficer() {
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вверх-вправо
            if (Frame.spis_coor_for_obvod[1] == 0 || Frame.spis_coor_for_obvod[0] == 7) {
                break;
            } //Если изначально конец карты
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0 || Frame.spis_coor_for_obvod[0] + j == 7) {
                break;
            } //Блок на конец карты
        }
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вверх-влево
            if (Frame.spis_coor_for_obvod[1] == 0 || Frame.spis_coor_for_obvod[0] == 0) {
                break;
            } //Если изначально конец карты
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0 || Frame.spis_coor_for_obvod[0] - j == 0) {
                break;
            } //Блок на конец карты
        }
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вниз-вправо
            if (Frame.spis_coor_for_obvod[1] == 7 || Frame.spis_coor_for_obvod[0] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7 || Frame.spis_coor_for_obvod[0] + j == 7) {
                break;
            }
        }
        for (int i = 1, j = 1; true; i++, j++) { //Шаги и убийства вниз-влево
            if (Frame.spis_coor_for_obvod[1] == 7 || Frame.spis_coor_for_obvod[0] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] < 100) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7 || Frame.spis_coor_for_obvod[0] - j == 0) {
                break;
            }
        }
    }

    public void korol() {
        //Офицерская реализация
        if (Frame.spis_coor_for_obvod[1] != 0 && Frame.spis_coor_for_obvod[0] != 7) { //вверх-вправо
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 0 && Frame.spis_coor_for_obvod[0] != 0) { //вверх-влево
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7 && Frame.spis_coor_for_obvod[0] != 7) {//вниз-вправо
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7 && Frame.spis_coor_for_obvod[0] != 0) {//вниз-влево
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        //---

        //Часть слона
        if (Frame.spis_coor_for_obvod[0] != 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[0] != 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] < 10) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 1;
            }
        }
    }

    public void horse() {

        if (Frame.spis_coor_for_obvod[1] > 1) {
            if (Frame.spis_coor_for_obvod[0] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] == 0) { //Два вверх-вправо
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] = 100;
                    }
                }
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[0] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] == 0) { //Два вверх-влево
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[1] < 6) {
            if (Frame.spis_coor_for_obvod[0] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] == 0) { //Два вних-вправо
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[0] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] == 0) { //Два вниз-влево
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[0] > 1) {
            if (Frame.spis_coor_for_obvod[1] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] == 0) { //Два влево-вверх
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[1] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] == 0) { //Два влево-вниз
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[0] < 6) {
            if (Frame.spis_coor_for_obvod[1] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] == 0) { //Два вправо-вверх
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[1] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] == 0) { //Два вправо-вниз
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] < 10) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] = 1;
                }
            }
        }
    }
}

class FigureRed { //Класс под красного игрока
    public void peshka() {
        //Разработка ходьбы
        if (Frame.spis_coor_for_obvod[1] == 1) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 100;
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0]] != 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 100;
            }
        } else {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] == 0 && Frame.spis_coor_for_obvod[1] != 7) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 100;
            }
        }
        //Разработка убийства
        if (Frame.spis_coor_for_obvod[0] == 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        } else if (Frame.spis_coor_for_obvod[0] == 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        } else {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] != 0 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
    }

    public void slone() {
        for (int i = 1; true; i++) { //Шаги и убийство вправо
            if (Frame.spis_coor_for_obvod[0] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + i] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[0] + i == 7) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства влево
            if (Frame.spis_coor_for_obvod[0] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - i] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[0] - i == 0) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства вверх
            if (Frame.spis_coor_for_obvod[1] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0]] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0) {
                break;
            }
        }
        for (int i = 1; true; i++) {//Шаги и убийства вниз
            if (Frame.spis_coor_for_obvod[1] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0]] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7) {
                break;
            }
        }
    }

    public void oficer() {
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вверх-вправо
            if (Frame.spis_coor_for_obvod[1] == 0 || Frame.spis_coor_for_obvod[0] == 7) {
                break;
            } //Если изначально конец карты
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] + j] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0 || Frame.spis_coor_for_obvod[0] + j == 7) {
                break;
            } //Блок на конец карты
        }
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вверх-влево
            if (Frame.spis_coor_for_obvod[1] == 0 || Frame.spis_coor_for_obvod[0] == 0) {
                break;
            } //Если изначально конец карты
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - i][Frame.spis_coor_for_obvod[0] - j] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] - i == 0 || Frame.spis_coor_for_obvod[0] - j == 0) {
                break;
            } //Блок на конец карты
        }
        for (int i = 1, j = 1; true; i++, j++) {//Шаги и убийства вниз-вправо
            if (Frame.spis_coor_for_obvod[1] == 7 || Frame.spis_coor_for_obvod[0] == 7) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] + j] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7 || Frame.spis_coor_for_obvod[0] + j == 7) {
                break;
            }
        }
        for (int i = 1, j = 1; true; i++, j++) { //Шаги и убийства вниз-влево
            if (Frame.spis_coor_for_obvod[1] == 7 || Frame.spis_coor_for_obvod[0] == 0) {
                break;
            }
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] = 1;
                break;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + i][Frame.spis_coor_for_obvod[0] - j] < 10) {
                break;
            }
            if (Frame.spis_coor_for_obvod[1] + i == 7 || Frame.spis_coor_for_obvod[0] - j == 0) {
                break;
            }
        }
    }

    public void korol() {
        //Офицерская реализация
        if (Frame.spis_coor_for_obvod[1] != 0 && Frame.spis_coor_for_obvod[0] != 7) { //вверх-вправо
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 0 && Frame.spis_coor_for_obvod[0] != 0) { //вверх-влево
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7 && Frame.spis_coor_for_obvod[0] != 7) {//вниз-вправо
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7 && Frame.spis_coor_for_obvod[0] != 0) {//вниз-влево
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        //---

        //Часть слона
        if (Frame.spis_coor_for_obvod[0] != 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] + 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[0] != 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1]][Frame.spis_coor_for_obvod[0] - 1] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 0) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0]] = 1;
            }
        }
        if (Frame.spis_coor_for_obvod[1] != 7) {
            if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] == 0) {
                Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 100;
            } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] < 100) {
                Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0]] = 1;
            }
        }
    }

    public void horse() {

        if (Frame.spis_coor_for_obvod[1] > 1) {
            if (Frame.spis_coor_for_obvod[0] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] == 0) { //Два вверх-вправо
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] = 100;
                    }
                }
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] + 1] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[0] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] == 0) { //Два вверх-влево
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 2][Frame.spis_coor_for_obvod[0] - 1] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[1] < 6) {
            if (Frame.spis_coor_for_obvod[0] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] == 0) { //Два вних-вправо
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] + 1] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[0] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] == 0) { //Два вниз-влево
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 2][Frame.spis_coor_for_obvod[0] - 1] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[0] > 1) {
            if (Frame.spis_coor_for_obvod[1] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] == 0) { //Два влево-вверх
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] - 2] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[1] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] == 0) { //Два влево-вниз
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] - 2] = 1;
                }
            }
        }
        if (Frame.spis_coor_for_obvod[0] < 6) {
            if (Frame.spis_coor_for_obvod[1] != 0) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] == 0) { //Два вправо-вверх
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] - 1][Frame.spis_coor_for_obvod[0] + 2] = 1;
                }
            }
            if (Frame.spis_coor_for_obvod[1] != 7) {
                if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] == 0) { //Два вправо-вниз
                    if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] == 0) {
                        Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] = 100;
                    }
                } else if (Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] > 10 && Frame.spis_game_pole[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] < 100) {
                    Frame.spis_game_pole_var[Frame.spis_coor_for_obvod[1] + 1][Frame.spis_coor_for_obvod[0] + 2] = 1;
                }
            }
        }
    }
}
