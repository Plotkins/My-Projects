#ifndef PRESENTERWINDOW_H
#define PRESENTERWINDOW_H

#include <QWidget>
#include "ui_presenterwindow.h"
#include "Repository.h"
#include "Observer.h"
#include "Exceptions.h"
#include <QMessageBox>
class PresenterWindow : public QWidget,public Observer
{
	Q_OBJECT

public:
	PresenterWindow(Repository* r,Subject& se,QWidget *parent = 0);
	~PresenterWindow();
	void populateQuestionList(vector<Question> q);
	void connectSignalsAndSlots();
	void update() override;

private:
	Ui::PresenterWindow ui;
	Repository* repo;
	Subject& session;
public slots:
	void addHandler();
};

#endif // PRESENTERWINDOW_H
