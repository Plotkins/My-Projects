#ifndef QUIZWINDOW_H
#define QUIZWINDOW_H

#include <QtWidgets/QMainWindow>
#include "ui_quizwindow.h"

class QuizWindow : public QMainWindow
{
	Q_OBJECT

public:
	QuizWindow(QWidget *parent = 0);
	~QuizWindow();

private:
	Ui::QuizWindowClass ui;
};

#endif // QUIZWINDOW_H
