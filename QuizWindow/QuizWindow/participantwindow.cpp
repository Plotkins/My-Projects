#include "participantwindow.h"

ParticipantWindow::ParticipantWindow(Participant* p,Repository* r, Subject& se, QWidget *parent)
	: QWidget(parent),participant(p),repo(r),session(se)
{
	ui.setupUi(this);
	session.registerObserver(this);
	setWindowTitle(QString::fromStdString(p->getName()));
	populateQuestionList(repo->getQuestionsByScore());
	connectSignalsAndSlots();
}

ParticipantWindow::~ParticipantWindow()
{

}

void ParticipantWindow::populateQuestionList(vector<Question> q)
{
	if (ui.questionList->count() > 0)
		ui.questionList->clear();
	for (auto s : q)
	{
		QString item = QString::fromStdString(s.toStrPart());
		QListWidgetItem *listItem = new QListWidgetItem{ item };
		if (participant->isId(s.getId()) == 1)
			listItem->setBackground(Qt::green);
		ui.questionList->addItem(listItem);
	}
	ui.scoreLine->setText(QString::fromStdString(to_string(participant->getScore())));
	ui.answerButton->setEnabled(true);
}

void ParticipantWindow::update()
{
	populateQuestionList(repo->getQuestionsByScore());
}

void ParticipantWindow::connectSignalsAndSlots()
{
	QObject::connect(ui.answerButton, SIGNAL(clicked()), this, SLOT(answerHandler()));

}

int ParticipantWindow::getItemClicked()
{
	if (this->ui.questionList->count() == 0)
		return -1;

	// get selected index
	QModelIndexList index = this->ui.questionList->selectionModel()->selectedIndexes();
	if (index.size() == 0)
		return -1;

	int idx = index.at(0).row();
	return idx;
}

void ParticipantWindow::answerHandler()
{
	string answer = ui.answerLine->text().toStdString();
	vector<Question> v = repo->getQuestionsByScore();
	int pos = getItemClicked();
	if (pos != -1)
	{
		if (v[pos].getAnswer() == answer && participant->isId(v[pos].getId()) == 0)
			repo->addScoreToParticipant(participant, v[pos].getScore(),v[pos].getId());
		participant->addId(v[pos].getId());
	}
	session.notify();
}
