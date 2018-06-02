export default {
  tableFields: [
    {
      name: '__slot:view-button',
      title: ''
    },
    {
      name: '__component:badge-column',
      title: '',
      dataClass: 'text-center'
    },
    {
      name: 'title',
      title: 'Title'
    },
    {
      name: 'company',
      title: 'Department'
    },
    {
      name: 'location',
      title: 'Location'
    }
  ],
  sortFunctions: {
    'name': function (item1, item2) {
      return item1 >= item2 ? 1 : -1
    },
    'email': function (item1, item2) {
      return item1 >= item2 ? 1 : -1
    }
  }
}
