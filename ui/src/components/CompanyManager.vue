<template>
    <div class="companies">
        <h1> Micronaut - Company Manager Demo</h1>
        <b-form @submit.prevent="validate() ? addCompany() : null">
            <b-form-group
                    id="input-group-1"
                    label-for="company-name"
            >
                <b-form-input
                        id="company-name"
                        v-model="newCompany.name"
                        required
                        placeholder="Enter Company Name"
                ></b-form-input>
            </b-form-group>
            <b-button :disabled="!validate()" type="submit" variant="primary">Save Company</b-button>
        </b-form>
        <br/>
        <br/>
        <b-alert show variant="success">
            Data received from {{ this.hostName }} : {{ this.hostPort }}
        </b-alert>
        <company-list class="company-list" :companies="companies" @deleteCompany="deleteCompany"></company-list>
    </div>
</template>

<script>
    import CompanyList from "./CompanyList";

    export default {
        name: 'CompanyManager',
        data() {
            return {
                companies: [],
                hostPort: -1,
                hostName: '',
                newCompany: {
                    name: ''
                }
            }
        },
        methods: {
            validate() {
                return (this.newCompany.name !== '')
            },
            async loadCompanies() {
                const response = await fetch(`${process.env.VUE_APP_SERVER_URL}/companies`);
                const respJson = await response.json();
                this.companies = respJson.companies;
                this.hostPort = respJson.hostPort;
                this.hostName = respJson.hostName
            },
            async addCompany() {
                await fetch(`${process.env.VUE_APP_SERVER_URL}/companies`,
                    {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(this.newCompany)
                    });
                this.newCompany.name = '';
                this.loadCompanies()
            },
            async deleteCompany(event) {
                await fetch(`${process.env.VUE_APP_SERVER_URL}/companies/${event.id}`, {
                    method: 'DELETE'
                })
                this.loadCompanies()
            }
        },
        components: {
            companyList: CompanyList
        },
        created() {
            this.loadCompanies()
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .companies {
        width: 75%;
        margin: 0 auto;
    }

    .company-list {
        margin-top: 50px;
    }
</style>
