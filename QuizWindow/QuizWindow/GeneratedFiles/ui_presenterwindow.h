/********************************************************************************
** Form generated from reading UI file 'presenterwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PRESENTERWINDOW_H
#define UI_PRESENTERWINDOW_H

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
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_PresenterWindow
{
public:
    QListWidget *questionList;
    QPushButton *addButton;
    QWidget *widget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QLabel *label;
    QLineEdit *idLine;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label_2;
    QLineEdit *textLine;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label_3;
    QLineEdit *answerLine;
    QHBoxLayout *horizontalLayout_4;
    QLabel *label_4;
    QLineEdit *scoreLine;

    void setupUi(QWidget *PresenterWindow)
    {
        if (PresenterWindow->objectName().isEmpty())
            PresenterWindow->setObjectName(QStringLiteral("PresenterWindow"));
        PresenterWindow->resize(608, 447);
        questionList = new QListWidget(PresenterWindow);
        questionList->setObjectName(QStringLiteral("questionList"));
        questionList->setGeometry(QRect(20, 20, 321, 401));
        addButton = new QPushButton(PresenterWindow);
        addButton->setObjectName(QStringLiteral("addButton"));
        addButton->setGeometry(QRect(370, 230, 181, 23));
        widget = new QWidget(PresenterWindow);
        widget->setObjectName(QStringLiteral("widget"));
        widget->setGeometry(QRect(370, 60, 221, 161));
        verticalLayout = new QVBoxLayout(widget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        label = new QLabel(widget);
        label->setObjectName(QStringLiteral("label"));

        horizontalLayout->addWidget(label);

        idLine = new QLineEdit(widget);
        idLine->setObjectName(QStringLiteral("idLine"));

        horizontalLayout->addWidget(idLine);


        verticalLayout->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QStringLiteral("horizontalLayout_2"));
        label_2 = new QLabel(widget);
        label_2->setObjectName(QStringLiteral("label_2"));

        horizontalLayout_2->addWidget(label_2);

        textLine = new QLineEdit(widget);
        textLine->setObjectName(QStringLiteral("textLine"));

        horizontalLayout_2->addWidget(textLine);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QStringLiteral("horizontalLayout_3"));
        label_3 = new QLabel(widget);
        label_3->setObjectName(QStringLiteral("label_3"));

        horizontalLayout_3->addWidget(label_3);

        answerLine = new QLineEdit(widget);
        answerLine->setObjectName(QStringLiteral("answerLine"));

        horizontalLayout_3->addWidget(answerLine);


        verticalLayout->addLayout(horizontalLayout_3);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setObjectName(QStringLiteral("horizontalLayout_4"));
        label_4 = new QLabel(widget);
        label_4->setObjectName(QStringLiteral("label_4"));

        horizontalLayout_4->addWidget(label_4);

        scoreLine = new QLineEdit(widget);
        scoreLine->setObjectName(QStringLiteral("scoreLine"));

        horizontalLayout_4->addWidget(scoreLine);


        verticalLayout->addLayout(horizontalLayout_4);


        retranslateUi(PresenterWindow);

        QMetaObject::connectSlotsByName(PresenterWindow);
    } // setupUi

    void retranslateUi(QWidget *PresenterWindow)
    {
        PresenterWindow->setWindowTitle(QApplication::translate("PresenterWindow", "PresenterWindow", 0));
        addButton->setText(QApplication::translate("PresenterWindow", "Add question", 0));
        label->setText(QApplication::translate("PresenterWindow", "ID", 0));
        label_2->setText(QApplication::translate("PresenterWindow", "Text", 0));
        label_3->setText(QApplication::translate("PresenterWindow", "Correct answer\"", 0));
        label_4->setText(QApplication::translate("PresenterWindow", "Score", 0));
    } // retranslateUi

};

namespace Ui {
    class PresenterWindow: public Ui_PresenterWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PRESENTERWINDOW_H
