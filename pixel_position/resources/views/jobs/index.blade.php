<x-layout>
    <div class="space-y-10">
        <section class="pt-6 text-center">
            <h1 class="text-4xl font-bold">Let's Find Your Next Job</h1>

            <form action="" class="mt-6">
                <input type="text" placeholder="Web Developer..."
                    class="w-full max-w-xl rounded-xl border-white/10 bg-white/5 px-5 py-4">
            </form>
        </section>

        <section class="pt-10">
            <x-section-heading>Featured Jobs</x-section-heading>

            <div class="mt-6 grid gap-8 lg:grid-cols-3">
                @foreach ($featuredJobListings as $jobListing)
                    <x-job-card :jobListing="$jobListing" />
                @endforeach
            </div>
        </section>

        <section>
            <x-section-heading>Tags</x-section-heading>

            <div class="mt-6 space-x-1">
                @foreach ($tags as $tag)
                    <x-tag :tag="$tag" />
                @endforeach
            </div>
        </section>

        <section>
            <x-section-heading>Recent Jobs</x-section-heading>

            <div class="mt-6 space-y-6">
                @foreach ($jobListings as $jobListing)
                    <x-job-card-wide :jobListing="$jobListing" />
                @endforeach
            </div>
        </section>
    </div>
</x-layout>