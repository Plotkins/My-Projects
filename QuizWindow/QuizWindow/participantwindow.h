#ifndef PARTICIPANTWINDOW_H
#define PARTICIPANTWINDOW_H

#include <QWidget>
#include "ui_participantwindow.h"
#include "Participant.h"
#include "Repository.h"
#include "Question.h"
#include "Observer.h"
class ParticipantWindow : public QWidget,public Observer
{
	Q_OBJECT

public:
	ParticipantWindow(Participant* p,Repository* r,Subject& se,QWidget *parent = 0);
	~ParticipantWindow();
	void populateQuestionList(vector<Question> q);
	void update() override;
	void connectSignalsAndSlots();
	int getItemClicked();
private:
	Ui::ParticipantWindow ui;
	Participant* participant;
	Repository* repo;
	Subject& session;
public slots:
	void answerHandler();
	
};

#endif // PARTICIPANTWINDOW_H
