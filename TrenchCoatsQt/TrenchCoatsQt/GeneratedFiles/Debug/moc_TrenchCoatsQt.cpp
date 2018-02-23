/****************************************************************************
** Meta object code from reading C++ file 'TrenchCoatsQt.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.6.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../TrenchCoatsQt.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'TrenchCoatsQt.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.6.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_TrenchCoatsQt_t {
    QByteArrayData data[11];
    char stringdata0[121];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_TrenchCoatsQt_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_TrenchCoatsQt_t qt_meta_stringdata_TrenchCoatsQt = {
    {
QT_MOC_LITERAL(0, 0, 13), // "TrenchCoatsQt"
QT_MOC_LITERAL(1, 14, 15), // "listItemChanged"
QT_MOC_LITERAL(2, 30, 0), // ""
QT_MOC_LITERAL(3, 31, 10), // "addNewCoat"
QT_MOC_LITERAL(4, 42, 10), // "deleteCoat"
QT_MOC_LITERAL(5, 53, 10), // "updateCoat"
QT_MOC_LITERAL(6, 64, 15), // "filterRepoCoats"
QT_MOC_LITERAL(7, 80, 16), // "moveCoatToBasket"
QT_MOC_LITERAL(8, 97, 9), // "openImage"
QT_MOC_LITERAL(9, 107, 7), // "shuffle"
QT_MOC_LITERAL(10, 115, 5) // "order"

    },
    "TrenchCoatsQt\0listItemChanged\0\0"
    "addNewCoat\0deleteCoat\0updateCoat\0"
    "filterRepoCoats\0moveCoatToBasket\0"
    "openImage\0shuffle\0order"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_TrenchCoatsQt[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       9,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   59,    2, 0x08 /* Private */,
       3,    0,   60,    2, 0x08 /* Private */,
       4,    0,   61,    2, 0x08 /* Private */,
       5,    0,   62,    2, 0x08 /* Private */,
       6,    0,   63,    2, 0x08 /* Private */,
       7,    0,   64,    2, 0x08 /* Private */,
       8,    0,   65,    2, 0x08 /* Private */,
       9,    0,   66,    2, 0x08 /* Private */,
      10,    0,   67,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void TrenchCoatsQt::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        TrenchCoatsQt *_t = static_cast<TrenchCoatsQt *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->listItemChanged(); break;
        case 1: _t->addNewCoat(); break;
        case 2: _t->deleteCoat(); break;
        case 3: _t->updateCoat(); break;
        case 4: _t->filterRepoCoats(); break;
        case 5: _t->moveCoatToBasket(); break;
        case 6: _t->openImage(); break;
        case 7: _t->shuffle(); break;
        case 8: _t->order(); break;
        default: ;
        }
    }
    Q_UNUSED(_a);
}

const QMetaObject TrenchCoatsQt::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_TrenchCoatsQt.data,
      qt_meta_data_TrenchCoatsQt,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *TrenchCoatsQt::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *TrenchCoatsQt::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_TrenchCoatsQt.stringdata0))
        return static_cast<void*>(const_cast< TrenchCoatsQt*>(this));
    return QWidget::qt_metacast(_clname);
}

int TrenchCoatsQt::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 9)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 9;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 9)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 9;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
