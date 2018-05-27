<template>
  <div class="dashboard">

    <dashboard-info-widgets></dashboard-info-widgets>

    <vuestic-widget class="no-padding no-v-padding">
      <vuestic-tabs
        :names="[$t('dashboard.dataVisualization'), $t('dashboard.usersAndMembers'), 
                 $t('dashboard.setupProfile'), $t('dashboard.features')]"
        ref="tabs">
        <div :slot="$t('dashboard.dataVisualization')">
          <data-visualisation-tab></data-visualisation-tab>
        </div>
        <div :slot="$t('dashboard.usersAndMembers')">
          <users-members-tab></users-members-tab>
        </div>
        <div :slot="$t('dashboard.setupProfile')">
          <setup-profile-tab></setup-profile-tab>
        </div>
        <div :slot="$t('dashboard.features')">
          <features-tab></features-tab>
        </div>
      </vuestic-tabs>
    </vuestic-widget>

    <dashboard-bottom-widgets></dashboard-bottom-widgets>

  </div>
</template>

<script>
  import DashboardInfoWidgets from './DashboardInfoWidgets'
  import UsersMembersTab from './users-and-members-tab/UsersMembersTab.vue'
  import SetupProfileTab from './setup-profile-tab/SetupProfileTab.vue'
  import FeaturesTab from './features-tab/FeaturesTab.vue'
  import DataVisualisationTab from './data-visualisation-tab/DataVisualisation.vue'
  import DashboardBottomWidgets from './DashboardBottomWidgets.vue'
  import axios from 'axios'

  export const AXIOS = axios.create({
    baseURL: process.env.API_BASE_URL
  })

  export default {
    name: 'dashboard',

    components: {
      DataVisualisationTab,
      DashboardInfoWidgets,
      UsersMembersTab,
      SetupProfileTab,
      FeaturesTab,
      DashboardBottomWidgets
    },

    methods: {
      launchEpicmaxToast () {
        this.showToast(`Let's work together!`, {
          icon: 'fa-star-o',
          position: 'top-right',
          duration: Infinity,
          action: {
            text: 'Hire us',
            href: 'http://epicmax.co/#/contact',
            class: 'vuestic-toasted-link'
          }
        })
      }
    },
    created () {
      var mailObj = {
        to: 'hanky0301@gmail.com',
        subject: '[Test]',
        text: '<!DOCTYPE html><html><body><h1>This is a Heading</h1><p>This is a paragraph.</p></body></html>'
      }
      console.log(mailObj)
      AXIOS.post(`/mail/send`, mailObj)
    }
  }

</script>
<style lang="scss" scoped>
  @import "../../sass/_variables.scss";
</style>
