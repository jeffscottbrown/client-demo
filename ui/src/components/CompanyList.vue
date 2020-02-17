<template>

    <table class="table table-hover table-striped" v-if="companies.length > 0">
        <thead>
        <tr>
            <td></td>
            <td>Company Name</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="company in companies" :key="company.id">
            <td>
                <form @submit.prevent="deleteCompany(company.id)">
                    <input class="btn btn-danger" type="submit" value="Delete"/>
                </form>
            </td>
            <td>{{ company.name }}</td>
        </tr>
        </tbody>
    </table>
    <p v-else>
    </p>
</template>

<script>
    export default {
        props: {
            companies: {
                type: Array,
                required: true
            }
        },
        methods: {
            deleteCompany(id) {
                this.$emit('deleteCompany', {
                    id
                })
            }
        }
    }
</script>
