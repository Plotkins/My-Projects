#ifndef TRENCHCOATSQT_H
#define TRENCHCOATSQT_H

#include <QtWidgets/QMainWindow>
#include "Controller.h"
#include <QListWidget>
#include <QFormLayout>
#include <QLineEdit>
#include <QPushButton>
#include <QLabel>
#include <qradiobutton.h>
#include <string>
class TrenchCoatsQt : public QWidget
{
	Q_OBJECT;

public:
	TrenchCoatsQt(Controller& c, QWidget* parent = 0);
	~TrenchCoatsQt();
private:
	Controller& ctrl;
	std::vector<Coat> currentCoatsInRepo;
	std::vector<Coat> currentCoatsInBasket;
	std::string oldSize;
	std::string oldColour;

	QListWidget* repoList;
	QLineEdit* colourEdit;
	QLineEdit* sizeEdit;
	QLineEdit* priceEdit;
	QLineEdit* quantityEdit;
	QLineEdit* lengthEdit;
	QLineEdit* linkEdit;
	QLineEdit* totalSumEdit;
	QPushButton* addButton;
	QPushButton* deleteButton;
	QPushButton* updateButton;
	QPushButton* filterButton;
	QPushButton* moveOneCoatButton;
	QPushButton* moveAllCoatsButton;
	QPushButton* openImageButton;

	QRadioButton* orderButton;
	QRadioButton* shuffleButton;

	QListWidget* shoppingList;

	void initGUI();
	void populateRepoList();
	void populateShoppingList();
	int getRepoListSelectedIndex();

	
	void connectSignalsAndSlots();

private slots:
	// When an item in the list is clicked, the text boxes get filled with the item's information
	void listItemChanged()
	{
		int idx = this->getRepoListSelectedIndex();
		if (idx == -1)
			return;

		std::vector<Coat> songs = this->currentCoatsInRepo;

		// get the song at the selected index
		if (idx >= songs.size())
			return;
		Coat s = songs[idx];

		this->colourEdit->setText(QString::fromStdString(s.getColour()));
		this->sizeEdit->setText(QString::fromStdString(s.getSize()));
		this->priceEdit->setText(QString::fromStdString(std::to_string(s.getPrice())));
		this->quantityEdit->setText(QString::fromStdString(std::to_string(s.getQuantity())));
		this->lengthEdit->setText(QString::fromStdString(std::to_string(s.getLength())));
		this->linkEdit->setText(QString::fromStdString(s.getLink()));
	}

	void addNewCoat();
	void deleteCoat();
	void updateCoat();

	// filters the elements in the list, by the artist written in the artist edit box
	void filterRepoCoats();

	void moveCoatToBasket();

	void openImage();

	void shuffle();
	void order();

};
#endif
