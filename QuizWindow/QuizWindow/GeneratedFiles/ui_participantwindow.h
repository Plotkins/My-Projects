/********************************************************************************
** Form generated from reading UI file 'participantwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PARTICIPANTWINDOW_H
#define UI_PARTICIPANTWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ParticipantWindow
{
public:
    QListWidget *questionList;
    QPushButton *answerButton;
    QLineEdit *scoreLine;
    QLabel *label_2;
    QWidget *widget;
    QHBoxLayout *horizontalLayout;
    QLabel *label;
    QLineEdit *answerLine;

    void setupUi(QWidget *ParticipantWindow)
    {
        if (ParticipantWindow->objectName().isEmpty())
            ParticipantWindow->setObjectName(QStringLiteral("ParticipantWindow"));
        ParticipantWindow->resize(471, 416);
        questionList = new QListWidget(ParticipantWindow);
        questionList->setObjectName(QStringLiteral("questionList"));
        questionList->setGeometry(QRect(10, 20, 321, 301));
        answerButton = new QPushButton(ParticipantWindow);
        answerButton->setObjectName(QStringLiteral("answerButton"));
        answerButton->setGeometry(QRect(20, 370, 75, 23));
        scoreLine = new QLineEdit(ParticipantWindow);
        scoreLine->setObjectName(QStringLiteral("scoreLine"));
        scoreLine->setGeometry(QRect(350, 50, 113, 20));
        label_2 = new QLabel(ParticipantWindow);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(390, 30, 47, 13));
        widget = new QWidget(ParticipantWindow);
        widget->setObjectName(QStringLiteral("widget"));
        widget->setGeometry(QRect(20, 340, 311, 22));
        horizontalLayout = new QHBoxLayout(widget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(widget);
        label->setObjectName(QStringLiteral("label"));

        horizontalLayout->addWidget(label);

        answerLine = new QLineEdit(widget);
        answerLine->setObjectName(QStringLiteral("answerLine"));

        horizontalLayout->addWidget(answerLine);


        retranslateUi(ParticipantWindow);

        QMetaObject::connectSlotsByName(ParticipantWindow);
    } // setupUi

    void retranslateUi(QWidget *ParticipantWindow)
    {
        ParticipantWindow->setWindowTitle(QApplication::translate("ParticipantWindow", "ParticipantWindow", 0));
        answerButton->setText(QApplication::translate("ParticipantWindow", "Answer!", 0));
        label_2->setText(QApplication::translate("ParticipantWindow", "Score", 0));
        label->setText(QApplication::translate("ParticipantWindow", "Answer:", 0));
    } // retranslateUi

};

namespace Ui {
    class ParticipantWindow: public Ui_ParticipantWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PARTICIPANTWINDOW_H
